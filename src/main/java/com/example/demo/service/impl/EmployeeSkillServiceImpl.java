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

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {

        // ✅ Validate years of experience
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be >= 0");
        }

        // ✅ Validate proficiency level (case-sensitive)
        List<String> validLevels = Arrays.asList(
                "Beginner",
                "Intermediate",
                "Advanced",
                "Expert"
        );

        if (!validLevels.contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        // ✅ Fetch employee (managed entity)
        Employee employee = employeeRepository
                .findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (!employee.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }

        // ✅ Fetch skill (managed entity)
        Skill skill = skillRepository
                .findById(mapping.getSkill().getId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }

        // ✅ CRITICAL FIX: attach managed entities
        mapping.setEmployee(employee);
        mapping.setSkill(skill);

        // ✅ Save mapping
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
        EmployeeSkill employeeSkill = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mapping not found"));

        employeeSkill.setActive(false);
        employeeSkillRepository.save(employeeSkill);
    }
}
