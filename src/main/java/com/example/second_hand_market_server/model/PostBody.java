package com.example.second_hand_market_server.model;

import lombok.Data;


@Data
public class PostBody {
    private String token;
    private Long id;
    private Long userId;
    private String itemDescription;
    private String itemName;
    private double price;
}
