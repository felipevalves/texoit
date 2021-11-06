package com.texoit.shared;

import lombok.Getter;

@Getter
public class MyResponse {

    public static final int OK = 200;
    public static final int ERROR_500 = 500;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;

    private int statusCode;
    private String message;

    public MyResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
