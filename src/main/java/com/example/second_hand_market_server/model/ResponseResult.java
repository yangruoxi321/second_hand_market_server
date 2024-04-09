package com.example.second_hand_market_server.model;

import com.example.second_hand_market_server.util.ResponseStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T payload;

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>().setCode(ResponseStatus.SUCCESS.getCode()).setMessage(ResponseStatus.SUCCESS.getMessage());
    }

    public static <T> ResponseResult<T> success(T payload) {
        return new ResponseResult<T>().setCode(ResponseStatus.SUCCESS.getCode()).setMessage(ResponseStatus.SUCCESS.getMessage()).setPayload(payload);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<T>().setCode(ResponseStatus.FAIL.getCode()).setMessage(ResponseStatus.FAIL.getMessage());
    }

    public static <T> ResponseResult<T> fail(int code, String message) {
        return new ResponseResult<T>().setCode(code).setMessage(message);
    }
}
