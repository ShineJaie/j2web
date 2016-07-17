package com.j2web.web.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 关于文件处理的工具类
 */
public class MyFileUtils {

    /**
     * 网站根路径
     */
    public static String rootPath = "";

    /**
     * 删除指定路径的文件或目录
     * <br/>
     * 如果此路径名表示一个目录, 则会先删除目录下的内容再将目录删除, 所以该操作不是原子性的
     * <br/>
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
     * <br/>
     * 如果是一个目录, 则会先删除目录下的内容再将目录删除, 所以该操作不是原子性的
     * <br/>
     * 如果目录中还有目录, 则会引发递归动作
     *
     * @param file 要删除文件或目录
     * @return 当且仅当成功删除文件或目录时, 返回 true; 否则返回 false
     */
    private static boolean deleteFileHandle(File file) {

        if (file.exists()) {

            if (file.isFile()) {

                return file.delete();

            } else if (file.isDirectory()) {

                File files[] = file.listFiles();

                if (files != null) {
                    for (File item : files) {
                        deleteFileHandle(item);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
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
     * 根据原图片路径生成缩略图
     *
     * @param originalImg 原图所在路径
     * @param thumbnail   缩略图所在路径
     * @param width       缩略图宽度
     * @param height      缩略图高度
     * @throws Exception
     */
    public static void generThumbnail(String originalImg, String thumbnail, int width, int height) throws Exception {

        Image image = ImageIO.read(new File(originalImg));

        double thumbRatio = (double) width / (double) height;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            height = (int) (width / imageRatio);
        } else {
            width = (int) (height * imageRatio);
        }

        if (imageWidth < width && imageHeight < height) {
            width = imageWidth;
            height = imageHeight;
        } else if (imageWidth < width) {
            width = imageWidth;
        } else if (imageHeight < height) {
            height = imageHeight;
        }

        BufferedImage thumbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, width, height, null);

        ImageIO.write(thumbImage, "jpg", new File(thumbnail));
    }
}
