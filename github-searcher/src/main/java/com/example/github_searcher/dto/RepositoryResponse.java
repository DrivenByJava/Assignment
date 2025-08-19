package com.example.github_searcher.dto;

import java.time.Instant;

public record RepositoryResponse(
        Long id,
        String name,
        String description,
        String owner,
        String language,
        Integer stars,
        Integer forks,
        Instant lastUpdated
) {}
