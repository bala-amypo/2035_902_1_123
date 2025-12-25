package com.example.demo.service;

import com.example.demo.model.SearchQueryRecord;

import java.util.List;

public interface SearchQueryService {

    SearchQueryRecord saveQuery(SearchQueryRecord record);

    SearchQueryRecord getQueryById(long id);

    List<SearchQueryRecord> getQueriesForUser(long userId);

    List<Object> searchEmployeesBySkills(List<?> skills, long count);
}
