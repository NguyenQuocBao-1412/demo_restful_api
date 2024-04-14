package com.myproject.uniclub.exception;

public enum ErrorStatusCode {
    USER_EXISTED(400, "User đã tồn tại")
    ;

    ErrorStatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
