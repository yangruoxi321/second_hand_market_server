package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Respository.UserRepository;
import com.example.second_hand_market_server.model.RegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody RegisterBody body){
            userRepository.createNewUser(body.getEmail(), body.getUserName());
    }
}
