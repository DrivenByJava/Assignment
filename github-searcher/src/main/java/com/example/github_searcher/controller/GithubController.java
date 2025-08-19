package com.example.github_searcher.controller;

import com.example.github_searcher.dto.*;
import com.example.github_searcher.service.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github")
@Validated
public class GithubController {

    private final RepositoryService service;

    public GithubController(RepositoryService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> search(@Valid @RequestBody SearchRequest request) {
        var repos = service.searchAndSave(request);
        return ResponseEntity.ok(new SearchResponse("Repositories fetched and saved successfully", repos));
    }

    @GetMapping("/repositories")
    public ResponseEntity<ListResponse> getRepositories(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(required = false, defaultValue = "stars") String sort,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "50") int size
    ) {
        Sort s = switch (sort) {
            case "forks" -> Sort.by(Sort.Direction.DESC, "forks");
            case "updated" -> Sort.by(Sort.Direction.DESC, "lastUpdated");
            default -> Sort.by(Sort.Direction.DESC, "stars");
        };
        Pageable pageable = PageRequest.of(page, Math.min(size, 100), s);
        var params = new RetrieveRequestParams(language, minStars, sort);
        var pageRes = service.findStored(params, pageable);
        return ResponseEntity.ok(new ListResponse(pageRes.getContent()));
    }
}
