package com.example.second_hand_market_server.configuration;

import com.example.second_hand_market_server.properties.MinioProperties;
import com.example.second_hand_market_server.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Slf4j
public class MinioConfig {

    @Bean
    @Scope("singleton")
    public MinioUtil minioUtil(MinioProperties minioProperties) {
        log.info("Initializing MinioUtil with properties: {}", minioProperties);
        return new MinioUtil(
                minioProperties.getEndpoint(),
                minioProperties.getAccessKey(),
                minioProperties.getSecretKey(),
                minioProperties.getBucketName()
        );
    }
}