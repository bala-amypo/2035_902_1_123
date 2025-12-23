package com.example.demo.service;

import com.example.demo.model.SearchQueryRecord;
import java.util.List;

public interface SearchQueryService {

    SearchQueryRecord create(SearchQueryRecord record);

    SearchQueryRecord getById(Long id);

    List<SearchQueryRecord> getAll();
}
