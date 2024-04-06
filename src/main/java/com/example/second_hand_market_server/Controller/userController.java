package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Respository.UserRepository;
import com.example.second_hand_market_server.Service.UserService;
import com.example.second_hand_market_server.catchException.DuplicateKeyException;
import com.example.second_hand_market_server.model.RegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class userController {
    @Autowired
    private UserRepository userRepository;


   @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody RegisterBody body){
        try{
            userService.signUp(body.getEmail(), body.getUserName(), body.getPassword());
            //userRepository.createNewUser(body.getEmail(), body.getUserName());
        }catch (RuntimeException e) {
            if (e instanceof DataIntegrityViolationException) {
                String errorMessage = e.getMessage();
                if (errorMessage != null && errorMessage.contains("duplicate key value violates unique constraint")) {
                    throw new DuplicateKeyException("Email already exists: " + body.getEmail());
                }
            }
            throw e;
        }
    }
    @PostMapping("/email_login")
    public ResponseEntity<?> loginViaUserEmail(@RequestBody RegisterBody body){
            return userService.signInViaEmail(body.getEmail(),body.getPassword());
        }
    @PostMapping("/user_name_login")
    public void loginViaUserName(@RequestBody RegisterBody body) {
        userService.signInViaUserName(body.getUserName(), body.getPassword());
    }
}

