package com.j2web.web.utils.fileupload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.j2web.web.utils.MyFileUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传工具类
 */
public class FileUploadTools {

    private static final Logger logger = Logger.getLogger(FileUploadTools.class);

    /**
     * 删除指定路径的文件或目录
     * <br />
     * 如果此路径名表示一个目录, 则会先删除目录下的内容再将目录删除, 所以该操作不是原子性的
     * <br />
     * 如果目录中还有目录, 则会引发递归动作
     *
     * @param filePath 要删除文件或目录的路径
     * @return 当且仅当成功删除文件或目录时, 返回 true; 否则返回 false
     */
    public static boolean deleteFile(String filePath) {

        File file = new File(filePath);
        return deleteFileHandle(file);
    }

    /**
     * 删除指定的文件或目录
     * <br />
     * 如果是一个目录, 则会先删除目录下的内容再将目录删除, 所以该操作不是原子性的
     * <br />
     * 如果目录中还有目录, 则会引发递归动作
     *
     * @param file 要删除文件或目录
     * @return 当且仅当成功删除文件或目录时, 返回 true; 否则返回 false
     */
    private static boolean deleteFileHandle(File file) {

        if (file.exists()) {

            if (file.isFile()) {

                file.delete();

            } else if (file.isDirectory()) {

                File files[] = file.listFiles();

                for (int i = 0; i < files.length; i++) {
                    deleteFileHandle(files[i]);
                }
            }
            return file.delete();
        } else {
            return true;
        }
    }

    /**
     * 文件上传
     *
     * @param legalType  需要匹配的文件类型(默认图片类型), 1: 图片类型; 2: 附件类型.
     * @param request    MultipartHttpServletRequest auto passed
     * @param directPath 文件存放的路径, 比如: /upload/syslogos
     * @return map {status : success/error, msg : statusMsg,
     * files : LinkedList<FileMeta>, jsonres : LinkedList<FileMeta> as json format}
     */
    public static Map<String, Object> uploadFile(Integer legalType, MultipartHttpServletRequest request, String directPath) {

        Map<String, Object> res = new HashMap<>();

        LinkedList<FileMeta> files = new LinkedList<>();
        FileMeta fileMeta;

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(FileMeta.class, "src", "newFileName", "fileName", "fileSize", "fileType");

        //1. build an iterator
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;

        //2. get each file
        while (itr.hasNext()) {

            //2.1 get next MultipartFile
            mpf = request.getFile(itr.next());

            // 过滤不合法的文件
            if (!isLegalFile(legalType, mpf.getContentType())) {
                logger.info("---> Filter illegal file: " + mpf.getOriginalFilename() + ", contentType: " + mpf.getContentType());
                continue;
            }

            // 过滤过大的文件
            if ((mpf.getSize() / 1024) > getMaximum(legalType)) {
                logger.info("---> Filter too big file: " + mpf.getOriginalFilename() + ", size: " + mpf.getSize() / 1024);
                continue;
            }

            //2.2 最多上传 10 个文件
            if (files.size() >= 10) {

                logger.info("---> Reached the maximum number of file uploads");

                logger.info("---> File upload json result: " + JSON.toJSONString(files, filter));

                res.put("status", "error");
                res.put("msg", "一次上传最多10个");

                return res;
            }

            //2.3 create new fileMeta
            fileMeta = new FileMeta();
            fileMeta.setFileName(mpf.getOriginalFilename());
            fileMeta.setNewFileName(renameFile(mpf.getOriginalFilename()));
            fileMeta.setFileSize(mpf.getSize() / 1024 + " KB");
            fileMeta.setFileType(mpf.getContentType());

            try {
                fileMeta.setBytes(mpf.getBytes());

                // Creating the directory to store file
                String diskPath = MyFileUtils.getFileUploadPath() + directPath;
                File dir = new File(diskPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filePath = diskPath + "/" + fileMeta.getNewFileName();
                String src = directPath + "/" + fileMeta.getNewFileName();

                fileMeta.setFilePath(filePath);
                fileMeta.setSrc(src);

                // copy file to local disk
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filePath));

            } catch (IOException e) {
                e.printStackTrace();
                logger.error("---> Failed to upload file: " + e.getMessage());

                res.put("status", "error");
                res.put("msg", "文件上传失败");

                return res;
            }
            //2.4 add to files
            files.add(fileMeta);
        }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 KB","fileType":"image/png"},...]

        logger.info("---> File upload json result: " + JSON.toJSONString(files, filter));

        res.put("status", "success");
        res.put("msg", "文件上传成功");
        res.put("files", files);
        res.put("jsonres", JSON.toJSONString(files, filter));

        return res;
    }

    /**
     * 根据文件类型判断文件是否合法
     *
     * @param type        需要匹配的文件类型(默认图片类型), 1: 图片类型; 2: 附件类型.
     * @param contentType 文件的本身类型
     * @return ture: 合法; false: 非法
     */
    public static boolean isLegalFile(Integer type, String contentType) {

        // 图片类型, 默认类型
        // gif, jpg, jpeg, png 类型
        List<String> legalTypes = new ArrayList<String>(Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif"));

        if (type != null && type == 2) {
            // 附件类型
            // gif, jpg, jpeg, png,
            // doc, xls, ppt, pdf, txt, rar, zip
            legalTypes.addAll(Arrays.asList(
                    "application/msword", "application/vnd.ms-excel", "application/vnd.ms-powerpoint",
                    "application/pdf", "text/plain", "application/octet-stream"));
        }

        if (contentType == null || contentType.isEmpty()) {
            return false;
        }

        return legalTypes.contains(contentType);
    }

    /**
     * 根据需要匹配的文件类型
     *
     * @param type 需要匹配的文件类型(默认图片类型), 1: 图片类型; 2: 附件类型.
     * @return maximum
     */
    public static long getMaximum(Integer type) {

        long maximum = 999 * 1024; // 上传图片最大为 999 KB

        if (type != null && type == 2) {
            maximum = 10 * 1024 * 1024; // 上传附件最大为 10 MB
        }

        return maximum;
    }

    /**
     * 文件重命名, 根据随机数和系统时间戳
     *
     * @return fileName
     */
    public static String renameFile(String fileName) {

        Random random = new Random();
        fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + getFileSuffix(fileName);
        return fileName;
    }

    /**
     * 获取文件后缀名
     *
     * @return fileSuffix
     */
    public static String getFileSuffix(String fileName) {

        return fileName.substring(fileName.lastIndexOf("."));
    }
}
