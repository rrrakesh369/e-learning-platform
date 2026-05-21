package com.elearning.exception;

public class BadRequestException extends RuntimeException{
    private final  int statusCode;

    public BadRequestException(String msg) {
        super(msg);
        statusCode = ExceptionCode.BAD_REQUEST;
    }
    public int getStatusCode() {
        return this.statusCode;
    }
}
