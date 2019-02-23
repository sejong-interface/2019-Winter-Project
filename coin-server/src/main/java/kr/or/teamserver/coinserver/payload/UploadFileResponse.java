package kr.or.teamserver.coinserver.payload;

public class UploadFileResponse {
    private final String fileName;
    private final String fileDownloadUrl;
    private final String fileType;
    private final long size;

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
}