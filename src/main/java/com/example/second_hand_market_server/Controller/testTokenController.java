package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.model.TokenBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class testTokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/testToken")
    public Long testToken(@RequestBody TokenBody body){
        Long user_id = tokenService.getUserIdByToken(body.getToken());
        return user_id;
    }
}
