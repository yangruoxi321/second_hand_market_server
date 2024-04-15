package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Response.ErrorResponse;
import com.example.second_hand_market_server.Respository.UserRepository;
import com.example.second_hand_market_server.catchException.DuplicateKeyException;
import com.example.second_hand_market_server.model.ProfileBody;
import com.example.second_hand_market_server.model.TokenBody;
import com.example.second_hand_market_server.util.Jwt;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("There is no such user"), HttpStatus.UNAUTHORIZED);
        }

        String userName = userRepository.findUserNameById(userId);
        String password = userRepository.getPassword(userId);
        boolean checkPasswordMatch = passwordEncoder.matches(rawPassword, password);

        if(!checkPasswordMatch) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Incorrect password"), HttpStatus.UNAUTHORIZED); //Incorrect password
        }

        String token = Jwt.generateToken(ImmutableMap.of("email", email, "username", userName, "userid", userId.toString()));
        tokenBody.setToken(token);
        return ResponseEntity.ok(tokenBody);
    }


    public  void signInViaUserName(String username, String rawPassword){

    }
    public ProfileBody getProfile(Long user_id){
        return userRepository.getProfile(user_id);
    }
}
