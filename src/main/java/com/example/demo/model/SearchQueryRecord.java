package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "search_query_records")
public class SearchQueryRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "searcher_id")
    private Long searcherId;
    
    @Column(name = "skills_requested")
    private String skillsRequested;
    
    @Column(name = "results_count")
    private Integer resultsCount;
    
    @Column(name = "searched_at")
    private Timestamp searchedAt;
    
    @PrePersist
    public void onCreate() {
        searchedAt = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    public void onUpdate() {
    }
    
    
    public SearchQueryRecord() {}
    
    public SearchQueryRecord(Long searcherId, String skillsRequested, Integer resultsCount) {
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
        this.resultsCount = resultsCount;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getSearcherId() { return searcherId; }
    public void setSearcherId(Long searcherId) { this.searcherId = searcherId; }
    
    public String getSkillsRequested() { return skillsRequested; }
    public void setSkillsRequested(String skillsRequested) { this.skillsRequested = skillsRequested; }
    
    public Integer getResultsCount() { return resultsCount; }
    public void setResultsCount(Integer resultsCount) { this.resultsCount = resultsCount; }
    
    public Timestamp getSearchedAt() { return searchedAt; }
    public void setSearchedAt(Timestamp searchedAt) { this.searchedAt = searchedAt; }
}