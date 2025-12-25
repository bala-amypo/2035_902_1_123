package com.example.demo.service;

import com.example.demo.model.SkillCategory;
import java.util.List;

public interface SkillCategoryService {
    SkillCategory createCategory(SkillCategory category);
    SkillCategory updateCategory(Long id, SkillCategory category);
    SkillCategory getCategoryById(Long id);
    List<SkillCategory> getAllCategories();
    void deactivate(Long id); // Updated to match compiler requirement 
}