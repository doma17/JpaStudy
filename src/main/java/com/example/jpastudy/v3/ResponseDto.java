package com.example.jpastudy.v3;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto<T> {
    private final ResponseStatus status;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    public ResponseDto(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
