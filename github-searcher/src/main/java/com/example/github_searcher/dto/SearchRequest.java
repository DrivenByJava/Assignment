package com.example.github_searcher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SearchRequest(
        @NotBlank String query,
        String language,
        @Pattern(regexp = "stars|forks|updated", message = "sort must be stars|forks|updated")
        String sort
) {}
