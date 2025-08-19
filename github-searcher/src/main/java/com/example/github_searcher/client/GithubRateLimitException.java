package com.example.github_searcher.client;

public class GithubRateLimitException extends RuntimeException {
    public GithubRateLimitException(String message) {
        super(message);
    }
}
