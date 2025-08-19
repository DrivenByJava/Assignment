package com.example.github_searcher.service;

import com.example.github_searcher.client.GithubRepoItem;
import com.example.github_searcher.client.GithubSearchClient;
import com.example.github_searcher.dto.RepositoryResponse;
import com.example.github_searcher.dto.RetrieveRequestParams;
import com.example.github_searcher.dto.SearchRequest;
import com.example.github_searcher.entity.RepositoryRecord;
import com.example.github_searcher.repository.RepositoryRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRecordRepository repo;
    private final GithubSearchClient github;

    public RepositoryService(RepositoryRecordRepository repo, GithubSearchClient github) {
        this.repo = repo;
        this.github = github;
    }

    @Transactional
    public List<RepositoryResponse> searchAndSave(SearchRequest req) {
        var result = github.searchRepositories(req.query(), req.language(), req.sort());
        if (result.items() == null || result.items().isEmpty()) return List.of();
        var entities = result.items().stream().map(this::toEntity).toList();
        var saved = repo.saveAll(entities);
        return saved.stream().map(this::toResponse).toList();
    }

    public Page<RepositoryResponse> findStored(RetrieveRequestParams params, Pageable pageable) {
        var spec = Specification.<RepositoryRecord>where(byLanguage(params.language()))
                .and(byMinStars(params.minStars()));
        var page = repo.findAll(spec, pageable);
        return page.map(this::toResponse);
    }

    private Specification<RepositoryRecord> byLanguage(String language) {
        return (root, cq, cb) -> {
            if (language == null || language.isBlank()) return null;
            return cb.equal(root.get("language"), language);
        };
    }

    private Specification<RepositoryRecord> byMinStars(Integer minStars) {
        return (root, cq, cb) -> {
            if (minStars == null) return null;
            return cb.greaterThanOrEqualTo(root.get("stars"), minStars);
        };
    }

    private RepositoryRecord toEntity(GithubRepoItem item) {
        RepositoryRecord e = new RepositoryRecord();
        e.setId(item.id());
        e.setName(item.name());
        e.setDescription(item.description());
        e.setOwner(item.owner() != null ? item.owner().login() : null);
        e.setLanguage(item.language());
        e.setStars(item.stargazersCount());
        e.setForks(item.forksCount());
        e.setLastUpdated(item.updatedAt());
        e.setHtmlUrl(item.htmlUrl());
        e.setOpenIssues(item.openIssuesCount() != null ? item.openIssuesCount() : 0);
        return e;
    }

    private RepositoryResponse toResponse(RepositoryRecord e) {
        return new RepositoryResponse(
                e.getId(), e.getName(), e.getDescription(), e.getOwner(),
                e.getLanguage(), e.getStars(), e.getForks(), e.getLastUpdated()
        );
    }
}
