package com.example.second_hand_market_server.configuration;

import com.example.second_hand_market_server.properties.AliOssProperties;
import com.example.second_hand_market_server.util.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Slf4j
public class OssConfig {
    @Bean()
    @Scope("singleton")
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("开始上传阿里云上传工具类对象: {}", aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
