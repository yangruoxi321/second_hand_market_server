package com.example.second_hand_market_server.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.second_hand_market_server.catchException.InvalidTokenException;
import com.example.second_hand_market_server.model.JwtTokenResult;
import com.example.second_hand_market_server.util.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtVerificationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean verifyResult = true;
        String errorMessage = "";
        String token = request.getHeader("token");
        JwtTokenResult tokenResult = null;
        try {
            tokenResult = Jwt.decodeToken(token);
        } catch (SignatureVerificationException e) {
            verifyResult = false;
            errorMessage = "Signature verification failed";
            System.out.println("Signature verification failed");
            throw new InvalidTokenException(errorMessage);
        } catch (TokenExpiredException e) {
            verifyResult = false;
            errorMessage = "Token expired";
            System.out.println("Token expired");
            throw new InvalidTokenException(errorMessage);
        } catch (AlgorithmMismatchException e) {
            verifyResult = false;
            errorMessage = "Algorithm mismatched";
            System.out.println( "Algorithm mismatched");
            throw new InvalidTokenException(errorMessage);
        } catch (Exception e) {
            verifyResult = false;
            errorMessage = "Invalid token";
            System.out.println("Invalid token");
            throw new InvalidTokenException(errorMessage);
        }
        return verifyResult;
    }
}