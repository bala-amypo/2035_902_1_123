package com.example.demo.service;

import com.example.demo.model.SearchQueryRecord;
import com.example.demo.model.EmployeeSkill;
import java.util.List;

public interface SearchQueryService {

    SearchQueryRecord create(SearchQueryRecord record);

    SearchQueryRecord getById(Long id);

    List<SearchQueryRecord> getAll();

    List<EmployeeSkill> searchEmployeesBySkills(List<Long> skillIds, long employeeId);
}
