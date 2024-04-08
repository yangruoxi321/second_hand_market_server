package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.model.JwtTokenResult;
import com.example.second_hand_market_server.util.Jwt;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String test_token(String token)  {
        JwtTokenResult jwtTokenResult = Jwt.decodeToken(token);
        System.out.println(jwtTokenResult.getUsername());
        System.out.println(jwtTokenResult.getEmail());
        System.out.println(jwtTokenResult.getUserid());
        System.out.println(jwtTokenResult);
        return jwtTokenResult.getEmail();
    }
    public Long getUserIdByToken(String token){
        JwtTokenResult jwtTokenResult = Jwt.decodeToken(token);
        String user_idString =  jwtTokenResult.getUserid();
        Long user_id = null;
        try{
            user_id = Long.valueOf(user_idString);
        }catch (NumberFormatException e){
            System.out.println("Invalid User id");
        }
        return user_id;
    }
    public String getEmailByToken(String token){
        JwtTokenResult jwtTokenResult = Jwt.decodeToken(token);
        return jwtTokenResult.getEmail();
    }
    public String getUserNameByToken(String token){
        JwtTokenResult jwtTokenResult = Jwt.decodeToken(token);
        return jwtTokenResult.getUsername();
    }
}
