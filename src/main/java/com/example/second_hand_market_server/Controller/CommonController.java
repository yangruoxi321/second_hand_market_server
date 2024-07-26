package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.util.MinioUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class CommonController {

    @Resource
    private MinioUtil minioUtil;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile file) {
        log.info("Begin file upload: {}", file.getOriginalFilename());
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID() + extension;

            String filePath = minioUtil.upload(file.getBytes(), objectName);

            if (filePath != null) {
                log.info("File successfully uploaded. Path: {}", filePath);
                return ResponseEntity.ok(filePath);
            } else {
                log.error("File upload failed. MinIO returned null path.");
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Upload failed: Internal server error");
            }
        } catch (IOException e) {
            log.error("File upload failed due to IO exception: ", e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Upload failed: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during file upload: ", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: Unexpected error occurred");
        }
    }
}