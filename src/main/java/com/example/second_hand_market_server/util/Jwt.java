package com.example.second_hand_market_server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class Jwt {
    //May move SIGNATURE to .env later for safety
    private static final String SIGNATURE = "eKKF2Q#T4fwpM@eJf3";

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
}
