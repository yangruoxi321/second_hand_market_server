package com.example.second_hand_market_server.util;

import io.minio.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@Slf4j
public class MinioUtil {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    /**
     * file upload
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(endpoint)
                            .credentials(accessKey, secretKey)
                            .build();

            // Make sure the bucket exists.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                // Make a new bucket called 'bucketName'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                log.info("Bucket '{}' already exists.", bucketName);
            }

            // Upload the byte array.
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    new ByteArrayInputStream(bytes), bytes.length, -1)
                            .build());
            log.info("'{}' is successfully uploaded as object '{}' to bucket '{}'.", objectName, objectName, bucketName);
        } catch (Exception e) {
            log.error("Error occurred: " + e);
            return null;
        }

        // Construct the file access path
        StringBuilder stringBuilder = new StringBuilder(endpoint);
        if (!endpoint.endsWith("/")) {
            stringBuilder.append("/");
        }
        stringBuilder
                .append(bucketName)
                .append("/")
                .append(objectName);

        log.info("file upload to: {}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}