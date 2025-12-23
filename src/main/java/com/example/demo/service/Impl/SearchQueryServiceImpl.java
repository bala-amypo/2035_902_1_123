package com.example.demo.serviceimpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository repository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public SearchQueryRecord create(SearchQueryRecord record) {
        return repository.save(record);
    }

    @Override
    public SearchQueryRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Search query not found"));
    }

    @Override
    public List<SearchQueryRecord> getAll() {
        return repository.findAll();
    }
}
