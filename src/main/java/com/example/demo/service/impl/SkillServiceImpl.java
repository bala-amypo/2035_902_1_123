package com.example.demo.service.impl;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) { // Constructor injection
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill createSkill(Skill skill) {
        skill.setActive(true);
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill existing = getSkillById(id);
        existing.setName(skillDetails.getName());
        return skillRepository.save(existing);
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public void deactivate(Long id) { // Renamed from deactivateSkill 
        Skill skill = getSkillById(id);
        skill.setActive(false);
        skillRepository.save(skill);
    }
}