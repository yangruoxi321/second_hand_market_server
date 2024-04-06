package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Service.TestToken;
import com.example.second_hand_market_server.model.testTokenBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testTokenController {
    @Autowired
    private TestToken testToken;

    @PostMapping("/testToken")
    public String testToken(@RequestBody testTokenBody body){
        return testToken.test_token(body.getToken());
    }
}
