package com.example.second_hand_market_server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.second_hand_market_server.model.JwtTokenResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.second_hand_market_server.constant.JwtInfo.*;
@Component
public class Jwt {
    //May move SIGNATURE to .env later for safety
    private static String SIGNATURE;

    @Value("${Jwt.SIGNATURE}")
    public void setSignature(String signature) {
        Jwt.SIGNATURE = signature;
    }

    public static String generateToken(Map<String, String> info) {
        // Store information
        JWTCreator.Builder jwtBuilder = JWT.create();
        info.forEach(jwtBuilder::withClaim);

        // Set expiration date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        Date date = calendar.getTime();

        jwtBuilder.withExpiresAt(date);
        // Sign the token
        return jwtBuilder.sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static Map<String, String> decryptToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        return jwt.getClaims().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toString()));
    }

    public static JwtTokenResult decodeToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        JwtTokenResult result = new JwtTokenResult();

        return result.setEmail(jwt.getClaim(EMAIL_KEY).asString())
                .setUsername(jwt.getClaim(USERNAME_KEY).asString())
                .setUserid(jwt.getClaim(USERID_KEY).asString());
    }
}
