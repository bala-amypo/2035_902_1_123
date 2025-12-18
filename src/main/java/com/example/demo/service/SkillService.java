package com.example.demo.service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillService {
    
    private final SkillRepository skillRepository;
    
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }
    
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }
    
    public Skill updateSkill(Long id, Skill skill) {
        Skill existing = getSkillById(id);
        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        existing.setDescription(skill.getDescription());
        return skillRepository.save(existing);
    }
    
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
    }
    
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    
    public void deactivateSkill(Long id) {
        Skill skill = getSkillById(id);
        skill.setActive(false);
        skillRepository.save(skill);
    }
}