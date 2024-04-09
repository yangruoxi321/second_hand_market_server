package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Entity.User;
import com.example.second_hand_market_server.Service.UserService;
import com.example.second_hand_market_server.model.RegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class userController {

   @Autowired
    private UserService userService;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody RegisterBody body){
        userService.signUp(body.getEmail(), body.getUserName(), body.getPassword());
    }
    @PostMapping("/email_login")
    public ResponseEntity<?> loginViaUserEmail(@RequestBody RegisterBody body){
            return userService.signInViaEmail(body.getEmail(),body.getPassword());
        }
    @PostMapping("/user_name_login")
    public void loginViaUserName(@RequestBody RegisterBody body) {
        userService.signInViaUserName(body.getUserName(), body.getPassword());
    }

    @PostMapping("/getUserById")
    public User getUserById(@RequestBody User body) {
       return userService.getUserById(body.getId());
    }
}

