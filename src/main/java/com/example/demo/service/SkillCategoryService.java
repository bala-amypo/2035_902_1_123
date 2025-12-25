package com.example.demo.service.impl;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        // TODO: Add implementation to save skill category
        return null;
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category) {
        // TODO: Add implementation to update category by id
        return null;
    }

    @Override
    public SkillCategory getById(Long id) {
        // TODO: Add implementation to fetch category by id
        return null;
    }

    @Override
    public List<SkillCategory> getAll() {
        // TODO: Add implementation to fetch all categories
        return null;
    }

    @Override
    public void deactivate(Long id) {
        // TODO: Add implementation to deactivate category by id
    }
}
