package com.example.demo.serviceimpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository repository;

    public SkillCategoryServiceImpl(SkillCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SkillCategory create(SkillCategory category) {
        return repository.save(category);
    }

    @Override
    public SkillCategory update(Long id, SkillCategory category) {
        SkillCategory existing = getById(id);
        existing.setCategoryName(category.getCategoryName());
        existing.setDescription(category.getDescription());
        return repository.save(existing);
    }

    @Override
    public SkillCategory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill category not found"));
    }

    @Override
    public List<SkillCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        SkillCategory category = getById(id);
        category.setActive(false);
        repository.save(category);
    }
}
