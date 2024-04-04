package com.example.second_hand_market_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.second_hand_market_server.Respository")

public class SecondHandMarketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondHandMarketServerApplication.class, args);
    }

}
