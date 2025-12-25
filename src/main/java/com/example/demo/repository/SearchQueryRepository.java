package com.example.demo.repository;

import com.example.demo.model.SearchQueryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchQueryRepository extends JpaRepository<SearchQueryRecord, Long> {

    // Get all search queries for an employee
    List<SearchQueryRecord> findByEmployeeId(Long employeeId);

    // Example: find by skill IDs if your entity has a collection of skills
    List<SearchQueryRecord> findBySkillsIdInAndEmployeeId(List<Long> skillIds, Long employeeId);
}
