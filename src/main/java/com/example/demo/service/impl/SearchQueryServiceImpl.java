package com.example.demo.service.impl;

import com.example.demo.model.SearchQueryRecord;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    @Override
    public SearchQueryRecord create(SearchQueryRecord record) {
        // TODO: Add implementation to save search query
        return null;
    }

    @Override
    public SearchQueryRecord getById(Long id) {
        // TODO: Add implementation to fetch search query by id
        return null;
    }

    @Override
    public List<SearchQueryRecord> getAll() {
        // TODO: Add implementation to fetch all search queries
        return null;
    }

    @Override
    public List<EmployeeSkill> searchEmployeesBySkills(List<Long> skillIds, long employeeId) {
        // TODO: Add implementation to search employees by skills
        return null;
    }
}
