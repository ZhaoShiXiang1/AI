package com.example.aihandler.service;

import com.example.aihandler.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    @Value("${external.api.url:https://api.deepseek.com/v1}")  // 在application.properties中配置
    private String externalApiUrl;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ApiResponse processAiContent(String content) {
        if (content == null || !content.contains("AI回答")) {
            return new ApiResponse(null, false, "请求内容不包含'AI回答'");
        }

        // 分割"AI回答"和实际内容
        String[] parts = content.split("AI回答", 2);
        String actualContent = parts.length > 1 ? parts[1].trim() : "";

        if (actualContent.isEmpty()) {
            return new ApiResponse(null, false, "分割后内容为空");
        }

        try {
            // 调用外部API
            String externalResponse = callExternalApi(actualContent);

            // 解析外部API响应（这里简单返回，实际应根据外部API响应格式解析）
            return new ApiResponse(externalResponse, true, "处理成功");
        } catch (Exception e) {
            return new ApiResponse(null, false, "调用外部API失败: " + e.getMessage());
        }
    }

    private String callExternalApi(String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求体
        String requestBody = "{\"content\": \"" + content + "\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        // 发送请求并获取响应
        ResponseEntity<String> response = restTemplate.postForEntity(
                externalApiUrl,
                request,
                String.class
        );

        return response.getBody();
    }
}