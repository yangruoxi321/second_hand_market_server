package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Respository.UserRepository;
import com.example.second_hand_market_server.constant.JwtInfo;
import com.example.second_hand_market_server.util.Jwt;
import com.google.common.collect.ImmutableMap;
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
    public String  signInViaEmail(String email, String rawPassword){
        email = email.toLowerCase();
        Integer userId =  userRepository.findUserByEmail(email);
        if(userId == null) return "There is no such user";
        String user_name = userRepository.findUserNameById(userId);
        String password = userRepository.getPassword(userId);
        boolean checkPassWordMatch = passwordEncoder.matches(rawPassword,password);
        if(!checkPassWordMatch) return "Incorrect password";
        String token = Jwt.generateToken(ImmutableMap.of(JwtInfo.EMAIL_KEY,email, JwtInfo.USERNAME_KEY, user_name, JwtInfo.USERID_KEY, userId.toString()));
        return token;
    }

    public  void signInViaUserName(String username, String rawPassword){

    }
}
