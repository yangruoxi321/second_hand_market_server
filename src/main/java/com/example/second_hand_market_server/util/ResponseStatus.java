package com.example.second_hand_market_server.util;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    /**
     * 1000 - 1099 verification related codes.
     */
    VERIFICATION_ERROR(1099, "Verification code error."),

    INVALID_TOKEN(2001, "Token is invalid"),

    SUCCESS(201, "success"),
    FAIL(0, "fail");

    private int code;
    private String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
