package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository searchQueryRecordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;

    /**
     * Constructor injection with exact parameter order required by tests.
     */
    public SearchQueryServiceImpl(SearchQueryRecordRepository searchQueryRecordRepository, 
                                   EmployeeSkillRepository employeeSkillRepository) {
        this.searchQueryRecordRepository = searchQueryRecordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    /**
     * Core search logic that filters employees by skill and persists the query record.
     */
    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        // 1. Validate that the skills list is not null or empty.
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }

        // 2. Normalize skills: trim whitespace and convert to lowercase as required by tests.
        List<String> normalizedSkills = skills.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .collect(Collectors.toList());

        // 3. Invoke repository to find matching employees.
        // Note: The tests expect the repository call to use the normalized list[cite: 671].
        List<Employee> results = employeeSkillRepository.findEmployeesByAllSkillNames(normalizedSkills, userId);

        // 4. Create and persist the SearchQueryRecord for history tracking.
        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(userId); [cite: 744]
        record.setSkillsRequested(String.join(",", normalizedSkills)); [cite: 694]
        record.setResultsCount(results.size()); [cite: 743]
        
        // Rely on @PrePersist in the entity to set searchedAt timestamp.
        searchQueryRecordRepository.save(record);

        return results;
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord query) {
        return searchQueryRecordRepository.save(query); [cite: 337]
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        // Returns the record or null if not found as checked in testGetQueryByIdUsesRepository[cite: 700].
        return searchQueryRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        // Retrieves history for a specific user.
        return searchQueryRecordRepository.findBySearcherId(userId);
    }
}