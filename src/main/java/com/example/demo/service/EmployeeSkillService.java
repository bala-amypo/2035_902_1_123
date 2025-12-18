package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeSkillService {
    
    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;
    
    public EmployeeSkillService(EmployeeSkillRepository employeeSkillRepository, 
                               EmployeeRepository employeeRepository, 
                               SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }
    
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        validateProficiency(mapping.getProficiencyLevel());
        validateExperience(mapping.getYearsOfExperience());
        
        Employee employee = employeeRepository.findById(mapping.getEmployee().getId())
            .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Skill skill = skillRepository.findById(mapping.getSkill().getId())
            .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
            
        if (!employee.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }
        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }
        
        return employeeSkillRepository.save(mapping);
    }
    
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));
        
        validateProficiency(mapping.getProficiencyLevel());
        validateExperience(mapping.getYearsOfExperience());
        
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());
        return employeeSkillRepository.save(existing);
    }
    
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }
    
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }
    
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill mapping = employeeSkillRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));
        mapping.setActive(false);
        employeeSkillRepository.save(mapping);
    }
    
    private void validateProficiency(String proficiency) {
        List<String> validLevels = Arrays.asList("Beginner", "Intermediate", "Advanced", "Expert");
        if (!validLevels.contains(proficiency)) {
            throw new IllegalArgumentException("Invalid proficiency");
        }
    }
    
    private void validateExperience(Integer years) {
        if (years < 0) {
            throw new IllegalArgumentException("Experience years");
        }
    }
}