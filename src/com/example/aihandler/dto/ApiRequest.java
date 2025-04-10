package com.example.aihandler.dto;

public class ApiRequest {
    private String content;

    // 构造方法、getter和setter
    public ApiRequest() {}

    public ApiRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}