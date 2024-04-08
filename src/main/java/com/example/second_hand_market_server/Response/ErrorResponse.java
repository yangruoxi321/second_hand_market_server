package com.example.second_hand_market_server.Response;
import lombok.Data;

@Data
public class ErrorResponse {
    private String errorMessage;

    public ErrorResponse(String error) {
        this.errorMessage = error;
    }
}
