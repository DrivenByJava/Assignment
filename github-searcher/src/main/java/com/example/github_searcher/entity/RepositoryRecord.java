package com.example.github_searcher.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "repositories", indexes = {
        @Index(name = "idx_language", columnList = "language"),
        @Index(name = "idx_stars", columnList = "stars"),
        @Index(name = "idx_forks", columnList = "forks"),
        @Index(name = "idx_updated", columnList = "last_updated")
})
public class RepositoryRecord {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 4000)
    private String description;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "language")
    private String language;

    @Column(name = "stars", nullable = false)
    private Integer stars;

    @Column(name = "forks", nullable = false)
    private Integer forks;

    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

    @Column(name = "html_url")
    private String htmlUrl;

    @Column(name = "open_issues")
    private Integer openIssues;

    public RepositoryRecord() {}

    public RepositoryRecord(Long id, String name, String description, String owner, String language,
                            Integer stars, Integer forks, Instant lastUpdated, String htmlUrl, Integer openIssues) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.language = language;
        this.stars = stars;
        this.forks = forks;
        this.lastUpdated = lastUpdated;
        this.htmlUrl = htmlUrl;
        this.openIssues = openIssues;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public Integer getStars() { return stars; }
    public void setStars(Integer stars) { this.stars = stars; }

    public Integer getForks() { return forks; }
    public void setForks(Integer forks) { this.forks = forks; }

    public Instant getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Instant lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getHtmlUrl() { return htmlUrl; }
    public void setHtmlUrl(String htmlUrl) { this.htmlUrl = htmlUrl; }

    public Integer getOpenIssues() { return openIssues; }
    public void setOpenIssues(Integer openIssues) { this.openIssues = openIssues; }
}
