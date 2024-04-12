package com.example.second_hand_market_server.model;

import lombok.Data;
@Data
public class ProfileBody {
    private Long id;
    private String email;
    private String userName;
    private double sellerRate;
}
