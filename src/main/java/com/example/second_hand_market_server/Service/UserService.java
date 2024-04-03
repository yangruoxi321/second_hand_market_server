package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void signUp(String email, String username){
        email = email.toLowerCase();
        userRepository.createNewUser(email,username);
    }
    public  void signIn(String email){

    }
}
