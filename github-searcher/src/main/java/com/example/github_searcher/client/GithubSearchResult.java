package com.example.github_searcher.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record GithubSearchResult(
        @JsonProperty("total_count") int totalCount,
        @JsonProperty("incomplete_results") boolean incompleteResults,
        List<GithubRepoItem> items
) {}
