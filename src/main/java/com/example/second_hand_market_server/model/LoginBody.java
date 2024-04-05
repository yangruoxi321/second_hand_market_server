package com.example.second_hand_market_server.model;

import lombok.Data;

@Data
public class LoginBody {
    String email;
    String userName;
    String password;
}
