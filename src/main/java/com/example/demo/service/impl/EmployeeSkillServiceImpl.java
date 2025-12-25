package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    // Exact constructor order required by test setup [cite: 63]
    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository, 
                                    EmployeeRepository employeeRepository, 
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository; [cite: 16, 63]
        this.employeeRepository = employeeRepository; [cite: 16, 63]
        this.skillRepository = skillRepository; [cite: 16, 63]
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        // 1. Validate experience years [cite: 403]
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be >= 0");
        }

        // 2. Validate proficiency level [cite: 426]
        List<String> validLevels = Arrays.asList("Beginner", "Intermediate", "Advanced", "Expert");
        if (!validLevels.contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        // 3. Validate Employee existence and active status [cite: 571]
        Employee emp = employeeRepository.findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found")); [cite: 583]
        if (!emp.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }

        // 4. Validate Skill existence and active status [cite: 594]
        Skill skill = skillRepository.findById(mapping.getSkill().getId())
                .orElseThrow(() -> new RuntimeException("Skill not found")); [cite: 584]
        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }

        return employeeSkillRepository.save(mapping); [cite: 293]
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
                .orElseThrow(() -> new RuntimeException("Mapping not found")); [cite: 546]
        es.setActive(false); [cite: 549]
        employeeSkillRepository.save(es); [cite: 547]
    }
}