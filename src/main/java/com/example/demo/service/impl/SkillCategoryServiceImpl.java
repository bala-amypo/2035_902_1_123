package com.example.demo.service.impl;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository repository;

    public SkillCategoryServiceImpl(SkillCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        return repository.save(category);
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category) {
        Optional<SkillCategory> existing = repository.findById(id);
        if (existing.isPresent()) {
            SkillCategory e = existing.get();
            e.setName(category.getName());
            e.setActive(category.isActive());
            return repository.save(e);
        }
        return null;
    }

    @Override
    public SkillCategory getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<SkillCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        repository.findById(id).ifPresent(e -> {
            e.setActive(false);
            repository.save(e);
        });
    }
}
