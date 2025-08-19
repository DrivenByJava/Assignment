package com.example.github_searcher.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public record GithubRepoItem(
        long id,
        String name,
        String description,
        GithubOwner owner,
        String language,
        @JsonProperty("stargazers_count") int stargazersCount,
        @JsonProperty("forks_count") int forksCount,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("html_url") String htmlUrl,
        @JsonProperty("open_issues_count") Integer openIssuesCount
) {}
