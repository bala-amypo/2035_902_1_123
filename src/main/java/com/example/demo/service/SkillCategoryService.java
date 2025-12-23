package com.example.demo.service;

import com.example.demo.model.SkillCategory;
import java.util.List;

public interface SkillCategoryService {

    SkillCategory create(SkillCategory category);

    SkillCategory update(Long id, SkillCategory category);

    SkillCategory getById(Long id);

    List<SkillCategory> getAll();

    void deactivate(Long id);
}
