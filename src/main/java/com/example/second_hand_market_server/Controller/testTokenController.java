package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.model.TokenBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testTokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/testToken")
    public String testToken(@RequestBody TokenBody body){
        return tokenService.test_token(body.getToken());
    }
}
