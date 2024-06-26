package com.example.second_hand_market_server.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtTokenResult {
    private String email;
    private String username;
    private String userid;
}
