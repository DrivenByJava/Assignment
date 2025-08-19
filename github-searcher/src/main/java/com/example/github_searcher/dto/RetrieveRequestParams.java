package com.example.github_searcher.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record RetrieveRequestParams(
        String language,
        @Min(0) Integer minStars,
        @Pattern(regexp = "stars|forks|updated", message = "sort must be stars|forks|updated")
        String sort
) {}
