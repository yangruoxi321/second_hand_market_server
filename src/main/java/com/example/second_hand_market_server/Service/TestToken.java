package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.model.JwtTokenResult;
import com.example.second_hand_market_server.util.Jwt;
import org.springframework.stereotype.Service;

@Service
public class TestToken {
    public String test_token(String token)  {
        JwtTokenResult jwtTokenResult = Jwt.decodeToken(token);
        return jwtTokenResult.getEmail();
    }
}
