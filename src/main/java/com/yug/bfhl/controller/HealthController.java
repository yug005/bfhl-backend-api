package com.yug.bfhl.controller;

import com.yug.bfhl.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<ApiResponse> getHealth() {
        ApiResponse response = new ApiResponse();
        response.setIs_success(true);
        return ResponseEntity.ok(response);
    }

}
