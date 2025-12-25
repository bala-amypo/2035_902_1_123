package com.example.demo.service.impl;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Override
    public Skill create(Skill skill) {
        // TODO: Add implementation to save skill
        return null;
    }

    @Override
    public Skill update(Long id, Skill skill) {
        // TODO: Add implementation to update skill by id
        return null;
    }

    @Override
    public Skill getById(Long id) {
        // TODO: Add implementation to fetch skill by id
        return null;
    }

    @Override
    public List<Skill> getAll() {
        // TODO: Add implementation to fetch all skills
        return null;
    }

    @Override
    public void deactivate(Long id) {
        // TODO: Add implementation to deactivate skill by id
    }
}
