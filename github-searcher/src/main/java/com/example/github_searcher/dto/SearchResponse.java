package com.example.github_searcher.dto;

import java.util.List;

public record SearchResponse(
        String message,
        List<RepositoryResponse> repositories
) {}
