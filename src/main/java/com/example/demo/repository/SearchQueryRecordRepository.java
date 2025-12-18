package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SearchQueryRecord;

import java.util.List;

@Repository
public interface SearchQueryRecordRepository extends JpaRepository<SearchQueryRecord, Long> {
    List<SearchQueryRecord> findBySearcherId(Long searcherId);
}