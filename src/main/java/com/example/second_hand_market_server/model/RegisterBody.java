package com.example.second_hand_market_server.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterBody {
    String email;
    String userName;
    String password;
}