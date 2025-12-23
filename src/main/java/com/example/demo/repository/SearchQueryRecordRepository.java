package com.example.demo.repository;

import com.example.demo.model.SearchQueryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchQueryRecordRepository
        extends JpaRepository<SearchQueryRecord, Long> {
}
