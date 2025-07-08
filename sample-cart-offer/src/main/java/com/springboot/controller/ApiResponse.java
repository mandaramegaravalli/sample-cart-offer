package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiResponse {
    private String response_msg;


    public ApiResponse(String response_msg) {
        this.response_msg=response_msg;
    }
}
