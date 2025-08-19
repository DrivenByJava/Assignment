package com.example.github_searcher.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

@Service
public class GithubSearchClient {

    private final RestClient githubRestClient;

    @Value("${github.api.search-endpoint}")
    private String searchEndpoint;

    public GithubSearchClient(RestClient githubRestClient) {
        this.githubRestClient = githubRestClient;
    }

    public GithubSearchResult searchRepositories(String query, String language, String sort) {
        String q = (language != null && !language.isBlank()) ? query + " language:" + language : query;

        DefaultUriBuilderFactory f = new DefaultUriBuilderFactory();
        URI uri = f.builder()
                .path(searchEndpoint)
                .queryParam("q", q)
                .queryParam("sort", mapSort(sort))
                .queryParam("order", "desc")
                .queryParam("per_page", 50)
                .build();

        var responseSpec = githubRestClient.get().uri(uri).retrieve()
                .onStatus(status -> status.value() == 429, (req, res) -> {
                    String body = res.getBody() != null ? new String(res.getBody().readAllBytes()) : "";
                    throw new GithubRateLimitException(body);
                })
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    String body = res.getBody() != null ? new String(res.getBody().readAllBytes()) : "";
                    throw new GithubApiException(res.getStatusCode().value(), body);
                });

        return responseSpec.body(GithubSearchResult.class);
    }

    private String mapSort(String sort) {
        if ("forks".equalsIgnoreCase(sort)) return "forks";
        if ("updated".equalsIgnoreCase(sort)) return "updated";
        return "stars";
    }
}
