package com.elearning.core.dto;

import lombok.Data;

@Data
public class CommonResponse<T>{
    public T data;
    public String message;
    public int code;

    public CommonResponse() {
    }

    public CommonResponse(T data, String message, int codes) {
        this.data = data;
        this.message = message;
        this.code = codes;
    }

    public CommonResponse(String message, int codes) {
        this.message = message;
        this.code = codes;
    }

    public CommonResponse(T data) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public CommonResponse(String message) {
        this.message = message;
    }

    public CommonResponse(int code) {
        this.code = code;
    }

    public CommonResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public CommonResponse(T data, int code) {
        this.data = data;
        this.code = code;
    }

}
