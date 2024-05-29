package com.example.jpastudy.v3;

public class ResponseUtil {

    public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>(ResponseStatus.SUCCESS, message, data);
    }

    public static <T> ResponseDto<T> fail(String message, T data) {
        return new ResponseDto<>(ResponseStatus.FAIL, message, data);
    }

    public static <T> ResponseDto<T> error(String message, T data) {
        return new ResponseDto<>(ResponseStatus.ERROR, message, data);
    }
}
