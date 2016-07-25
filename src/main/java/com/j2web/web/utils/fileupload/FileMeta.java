package com.j2web.web.utils.fileupload;

/**
 * 文件基本信息 jQueryFileUpload 使用
 */
public class FileMeta {

    private String fileName; // 源文件名, 带后缀
    private String newFileName; // 新文件名, 带后缀
    private String fileSize; // 文件大小(单位: KB)
    private String fileType; // 文件类型
    private String filePath; // 文件保存路径
    private String src; // 文件展示地址

    private byte[] bytes; // 文件字节数组

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}