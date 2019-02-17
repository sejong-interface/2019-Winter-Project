package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileStorageService fileStorageService;

    public String getFileName(MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        return fileName;
    }

    public String getFileDownloadUri(String fileName) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return fileDownloadUri;
    }

    public String getContentType(Resource resource, HttpServletRequest request) {
        String contentType = "application/octet-stream";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        return contentType;
    }
}