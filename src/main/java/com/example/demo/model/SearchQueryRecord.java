package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "search_query_records")
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private Integer resultsCount;

    private Timestamp searchedAt;

    @PrePersist
    public void onCreate() {
        searchedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
