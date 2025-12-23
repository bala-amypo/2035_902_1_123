package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository SkillCategoryRepository;

    public SkillCategoryServiceImpl(SkillCategoryRepository SkillCategoryRepository) {
        this.SkillCategoryRepository = SkillCategoryRepository;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        SkillCategoryRepository.findByCategoryName(category.getCategoryName())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Category already exists");
                });

        category.setActive(true);
        return SkillCategoryRepository.save(category);
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category) {
        SkillCategory existing = getCategoryById(id);

        existing.setCategoryName(category.getCategoryName());
        existing.setDescription(category.getDescription());

        return SkillCategoryRepository.save(existing);
    }

    @Override
    public SkillCategory getCategoryById(Long id) {
        return SkillCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("SkillCategory not found"));
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return SkillCategoryRepository.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        SkillCategory category = getCategoryById(id);
        category.setActive(false);
        SkillCategoryRepository.save(category);
    }
}
