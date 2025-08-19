package com.example.github_searcher.advice;

import com.example.github_searcher.client.GithubApiException;
import com.example.github_searcher.client.GithubRateLimitException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "validation_failed");
        body.put("details", ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> Map.of("field", fe.getField(), "message", fe.getDefaultMessage()))
                .toList());
        return body;
    }

    @ExceptionHandler(GithubRateLimitException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public Map<String, Object> handleRateLimit(GithubRateLimitException ex) {
        return Map.of("error", "github_rate_limit", "message", ex.getMessage());
    }

    @ExceptionHandler(GithubApiException.class)
    public ResponseEntity<Map<String, Object>> handleGithub(GithubApiException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(Map.of("error", "github_api_error", "message", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleOther(Exception ex) {
        return Map.of("error", "internal_error", "message", "Unexpected error");
    }
}
