package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Entity.User;
import com.example.second_hand_market_server.Response.ErrorResponse;
import com.example.second_hand_market_server.Respository.UserRepository;
import com.example.second_hand_market_server.catchException.DuplicateKeyException;
import com.example.second_hand_market_server.model.TokenBody;
import com.example.second_hand_market_server.util.Jwt;
import com.google.common.collect.ImmutableMap;

import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;


    public void signUp(String email, String username, String rawPassword){
        email = email.toLowerCase();
        String password = passwordEncoder.encode(rawPassword);
        try{
            userRepository.createNewUser(email,username,password);
        }catch (RuntimeException e) {
            if (e instanceof DataIntegrityViolationException) {
                String errorMessage = e.getMessage();
                if (errorMessage != null && errorMessage.contains("duplicate key value violates unique constraint")) {
                    throw new DuplicateKeyException("Email already exists: " + email);
                }
            }
            throw e;
        }
    }

    public ResponseEntity<?> signInViaEmail(String email, String rawPassword){
        TokenBody tokenBody = new TokenBody();
        email = email.toLowerCase();
        Long userId = userRepository.findUserIDByEmail(email);

        if(userId == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("There is no such user"));
        }

        String userName = userRepository.findUserNameById(userId);
        String password = userRepository.getPassword(userId);
        boolean checkPasswordMatch = passwordEncoder.matches(rawPassword, password);

        if(!checkPasswordMatch) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Incorrect password"));
        }

        String token = Jwt.generateToken(ImmutableMap.of("email", email, "username", userName, "userid", userId.toString()));
        tokenBody.setToken(token);
        return ResponseEntity.ok(tokenBody);
    }


    public  void signInViaUserName(String username, String rawPassword){

    }
    public User getUserById(Long user_id){
        return userRepository.getUserById(user_id);
    }

    public void rateSeller(Long id , Double rate){
        Double rateInDb =  userRepository.getSellerRate(id);
        Long numberOfPeopleRated  = userRepository.getNumberOfPeopleRated(id);

        Double totalRate = rateInDb * numberOfPeopleRated + rate;

        Long newNumberOfPeopleRated = numberOfPeopleRated + 1;

        Double newRate = totalRate / newNumberOfPeopleRated;

        userRepository.updateSellerRate(newRate,id);
    }
}
