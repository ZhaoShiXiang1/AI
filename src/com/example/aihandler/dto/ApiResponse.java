package com.example.aihandler.dto;

public class ApiResponse {
    private String result;
    private boolean success;
    private String message;

    // 构造方法、getter和setter
    public ApiResponse() {}

    public ApiResponse(String result, boolean success, String message) {
        this.result = result;
        this.success = success;
        this.message = message;
    }

    // 省略其他getter和setter...
}