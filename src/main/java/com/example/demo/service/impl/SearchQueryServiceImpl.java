package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SearchQueryService;
import java.util.*;
import java.util.stream.Collectors;

public class SearchQueryServiceImpl implements SearchQueryService {
    private final SearchQueryRecordRepository searchQueryRecordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository searchQueryRecordRepository, 
                                   EmployeeSkillRepository employeeSkillRepository) {
        this.searchQueryRecordRepository = searchQueryRecordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }

        // Trimming and distinct as per test testSearchEmployeesBySkillsTrimsAndNormalizes
        List<String> normalizedSkills = skills.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.toList());

        List<Employee> results = employeeSkillRepository.findEmployeesByAllSkillNames(normalizedSkills, userId);

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(userId);
        record.setSkillsRequested(String.join(",", normalizedSkills));
        record.setResultsCount(results.size());
        searchQueryRecordRepository.save(record);

        return results;
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord record) {
        return searchQueryRecordRepository.save(record);
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return searchQueryRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return searchQueryRecordRepository.findBySearcherId(userId);
    }
}