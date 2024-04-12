package com.example.second_hand_market_server.Response;

import lombok.Data;

@Data

public class ErrorResponse {
    private String message;

    public ErrorResponse(String error) {
        this.message = error;
    }
}
