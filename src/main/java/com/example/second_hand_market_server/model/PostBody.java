package com.example.second_hand_market_server.model;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class PostBody {
    private String token;
    private Long id;
    private Long userId;
    private String itemDescription;
    private String itemName;
    private double price;
    private String search;
}
