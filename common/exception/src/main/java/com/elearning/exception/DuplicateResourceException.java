package com.elearning.exception;

public class DuplicateResourceException extends RuntimeException{
    private final  int statusCode;

    public DuplicateResourceException(String msg) {
        super(msg);
        statusCode = ExceptionCode.DUPLICATE_RESOURCE_EXCEPTION;
    }
    public int getStatusCode() {
        return this.statusCode;
    }
}
