package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void signUp(String email, String username, String rawPassword){
        email = email.toLowerCase();
        String password = passwordEncoder.encode(rawPassword);
        userRepository.createNewUser(email,username,password);
    }
    public  void signIn(String email){

    }
}
