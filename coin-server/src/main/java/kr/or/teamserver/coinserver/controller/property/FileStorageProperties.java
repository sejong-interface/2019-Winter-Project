package kr.or.teamserver.coinserver.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String baseDir;

    public String getUploadDir() {
        return baseDir;
    }

    public void setUploadDir(String baseDir) {
        this.baseDir = baseDir;
    }
}