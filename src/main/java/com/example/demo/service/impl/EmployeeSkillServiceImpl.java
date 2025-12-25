package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EmployeeSkillService;
import java.util.List;
import java.util.Arrays;

public class EmployeeSkillServiceImpl implements EmployeeSkillService {
    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository, 
                                    EmployeeRepository employeeRepository, 
                                    com.example.demo.repository.SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        if (mapping.getYearsOfExperience() < 0) 
            throw new IllegalArgumentException("Experience years must be >= 0");
        
        List<String> validLevels = Arrays.asList("Beginner", "Intermediate", "Advanced", "Expert");
        if (!validLevels.contains(mapping.getProficiencyLevel()))
            throw new IllegalArgumentException("Invalid proficiency level");

        Employee emp = employeeRepository.findById(mapping.getEmployee().getId())
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (!emp.getActive()) throw new IllegalArgumentException("inactive employee");

        Skill skill = skillRepository.findById(mapping.getSkill().getId())
            .orElseThrow(() -> new RuntimeException("Skill not found"));
        if (!skill.getActive()) throw new IllegalArgumentException("inactive skill");

        return employeeSkillRepository.save(mapping);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = employeeSkillRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found"));
        es.setActive(false);
        employeeSkillRepository.save(es);
    }
}