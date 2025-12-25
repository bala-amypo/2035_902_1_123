package com.example.demo.controller;

import com.example.demo.dto.EmployeeSearchRequest;
import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    private final SearchQueryService service;

    public SearchQueryController(SearchQueryService service) {
        this.service = service;
    }

    @PostMapping("/employees")
    public List<Employee> searchEmployees(@RequestBody EmployeeSearchRequest request) {
        // Core search matrix endpoint as required by SRS
        // You should pass the searcherId from the current authenticated user context, 
        // but for basic compilation, we assume a static ID or one from the request.
        return service.searchEmployeesBySkills(request.getSkills(), 1L);
    }

    @GetMapping("/{id}")
    public SearchQueryRecord getById(@PathVariable Long id) {
        // Updated to use the correct service method name
        return service.getQueryById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SearchQueryRecord> getByUserId(@PathVariable Long userId) {
        // Updated to use the correct service method name
        return service.getQueriesForUser(userId);
    }
}