package com.example.second_hand_market_server.interceptor;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public JwtVerificationInterceptor jwtVerificationInterceptor() {
        return new JwtVerificationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtVerificationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(ImmutableList.of("/getAllPost", "/getPostByUserId", "/getPostByUserId", "/search","/email_login","/signup"));
    }
}