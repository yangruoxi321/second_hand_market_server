package com.example.second_hand_market_server.catchException;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String message) {
        super(message);
    }
}
