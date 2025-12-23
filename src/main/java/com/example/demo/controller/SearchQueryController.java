package com.example.demo.controller;

import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search-queries")
public class SearchQueryController {

    private final SearchQueryService service;

    public SearchQueryController(SearchQueryService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public SearchQueryRecord createSearchQuery(
            @RequestBody SearchQueryRecord record) {
        return service.create(record);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public SearchQueryRecord getSearchQueryById(
            @PathVariable Long id) {
        return service.getById(id);
    }

    // READ ALL
    @GetMapping
    public List<SearchQueryRecord> getAllSearchQueries() {
        return service.getAll();
    }
}
