package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.Service.UserService;
import com.example.second_hand_market_server.model.ProfileBody;
import com.example.second_hand_market_server.model.RegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
public class userController {
    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody RegisterBody body){
        userService.signUp(body.getEmail(), body.getUserName(), body.getPassword());
    }
    @PostMapping("/email_login")
    public ResponseEntity<?> loginViaUserEmail(@RequestBody RegisterBody body){
        try{
            userService.signInViaEmail(body.getEmail(),body.getPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }
    @PostMapping("/user_name_login")
    public void loginViaUserName(@RequestBody RegisterBody body) {
        userService.signInViaUserName(body.getUserName(), body.getPassword());
    }

    @PostMapping("/profile")
    public ProfileBody profile(@RequestHeader String token) {
        Long user_id = tokenService.getUserIdByToken(token);
        return userService.getProfile(user_id);
    }
}

