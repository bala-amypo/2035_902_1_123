package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchQueryService {
    
    private final SearchQueryRecordRepository searchQueryRecordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;
    
    public SearchQueryService(SearchQueryRecordRepository searchQueryRecordRepository, 
                             EmployeeSkillRepository employeeSkillRepository) {
        this.searchQueryRecordRepository = searchQueryRecordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }
    
    public SearchQueryRecord saveQuery(SearchQueryRecord query) {
        return searchQueryRecordRepository.save(query);
    }
    
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }
        
        List<Employee> employees = employeeSkillRepository.findEmployeesByAllSkillNames(skills, (long) skills.size());
        
        SearchQueryRecord query = new SearchQueryRecord(userId, String.join(",", skills), employees.size());
        searchQueryRecordRepository.save(query);
        
        return employees;
    }
    
    public SearchQueryRecord getQueryById(Long id) {
        return searchQueryRecordRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Query not found"));
    }
    
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return searchQueryRecordRepository.findBySearcherId(userId);
    }
}