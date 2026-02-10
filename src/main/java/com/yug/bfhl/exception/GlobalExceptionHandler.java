package com.yug.bfhl.exception;

import com.yug.bfhl.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiResponse> handleInvalidRequest(InvalidRequestException ex) {
        ApiResponse response = new ApiResponse();
        response.setIs_success(false);
        response.setError(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneral(Exception ex) {
        ApiResponse response = new ApiResponse();
        response.setIs_success(false);
        response.setError("Internal server error");
        return ResponseEntity.internalServerError().body(response);
    }

}
