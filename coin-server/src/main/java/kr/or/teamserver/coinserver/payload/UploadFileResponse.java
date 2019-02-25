package kr.or.teamserver.coinserver.payload;

import java.util.Objects;

public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUrl;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUrl, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUrl = fileDownloadUrl;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public long getSize() {
        return size;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override

    public String toString() {
        return "File{" +
                "FileName=" + fileName +
                ", Download Url=" + fileDownloadUrl +
                ", Type=" + fileType +
                ", Size=" + size +
                '}';
    }
}