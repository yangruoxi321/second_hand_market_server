package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.util.AliOssUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class CommonController {
    @Resource
    private AliOssUtil aliOssUtil;
    @PostMapping("/upload")
    public ResponseEntity upload(MultipartFile file) {
        log.info("Begin file upload: {}", file);
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf((".")));
            String objectName = UUID.randomUUID() + extension;
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return ResponseEntity.ok(filePath);
        } catch (IOException e) {
            log.error("file upload fail: {}", e);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("upload failed");
    }
}