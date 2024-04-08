package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.model.TokenBody;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testTokenController {
    @Resource
    private TokenService tokenService;

    @PostMapping("/testToken")
    public Long testToken(@RequestBody TokenBody body){
        Long user_id = tokenService.getUserIdByToken(body.getToken());
        return user_id;
    }
}
