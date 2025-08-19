package com.example.github_searcher.dto;

import java.util.List;

public record ListResponse(
        List<RepositoryResponse> repositories
) {}
