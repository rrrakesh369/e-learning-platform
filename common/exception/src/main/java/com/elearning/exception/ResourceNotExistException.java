package com.elearning.exception;

public class ResourceNotExistException extends RuntimeException{
    private final int statusCode;

    public ResourceNotExistException(String msg) {
        super(msg);
        statusCode = ExceptionCode.RESOURCE_NOT_FOUND_EXCEPTION;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
