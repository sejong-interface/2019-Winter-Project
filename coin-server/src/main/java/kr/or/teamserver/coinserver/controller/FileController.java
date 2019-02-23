package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.payload.UploadFileResponse;
import kr.or.teamserver.coinserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file")
public class FileController {

    private final Logger logger =  LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileService.storeFile(file);
        String fileDownloadUrl = fileService.getUrl(fileName);

        return new UploadFileResponse(fileName, fileDownloadUrl,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = fileService.getContentType(resource, request);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}