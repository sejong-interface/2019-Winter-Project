package kr.or.teamserver.coinserver.payload;

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