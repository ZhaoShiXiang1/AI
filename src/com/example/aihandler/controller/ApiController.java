package com.example.aihandler.controller;

import com.example.aihandler.dto.ApiRequest;
import com.example.aihandler.dto.ApiResponse;
import com.example.aihandler.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/process")
    public ApiResponse processRequest(@RequestBody ApiRequest request) {
        return apiService.processAiContent(request.getContent());
    }
}