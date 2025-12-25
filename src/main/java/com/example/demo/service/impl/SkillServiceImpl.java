package com.example.demo.service.impl;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    public Skill createSkill(Skill skill) {
        return repo.save(skill);
    }

    public Skill updateSkill(long id, Skill skill) {
        Skill s = repo.findById(id).orElseThrow();
        s.setName(skill.getName());
        return repo.save(s);
    }

    public void deactivateSkill(long id) {
        Skill s = repo.findById(id).orElseThrow();
        s.setActive(false);
        repo.save(s);
    }
}
