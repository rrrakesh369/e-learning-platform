package com.elearning.exception;

public class DuplicateResourceException extends RuntimeException{
    private final  int statusCode;

    public DuplicateResourceException(int statusCode) {
        this.statusCode = statusCode;
    }
}
