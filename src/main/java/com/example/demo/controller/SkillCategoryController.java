package com.example.demo.controller;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill-categories")
public class SkillCategoryController {

    private final SkillCategoryService service;

    public SkillCategoryController(SkillCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public SkillCategory createCategory(@RequestBody SkillCategory category) {
        return service.create(category);
    }

    @PutMapping("/{id}")
    public SkillCategory updateCategory(
            @PathVariable Long id,
            @RequestBody SkillCategory category) {
        return service.update(id, category);
    }

    @GetMapping("/{id}")
    public SkillCategory getCategory(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<SkillCategory> getAllCategories() {
        return service.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateCategory(@PathVariable Long id) {
        service.deactivate(id);
    }
}
