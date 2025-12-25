package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_query_records")
public class SearchQueryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private Integer resultsCount = 0;
    private LocalDateTime searchedAt;

    public SearchQueryRecord() {}

    /**
     * Sets the searchedAt timestamp automatically before persisting.
     */
    @PrePersist
    public void onCreate() {
        [cite_start]this.searchedAt = LocalDateTime.now(); [cite: 364]
        if (this.resultsCount == null) {
            [cite_start]this.resultsCount = 0; [cite: 365]
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSearcherId() {
        return searcherId;
    }

    public void setSearcherId(Long searcherId) {
        this.searcherId = searcherId;
    }

    public String getSkillsRequested() {
        return skillsRequested;
    }

    public void setSkillsRequested(String skillsRequested) {
        this.skillsRequested = skillsRequested;
    }

    public Integer getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    public LocalDateTime getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(LocalDateTime searchedAt) {
        this.searchedAt = searchedAt;
    }
}