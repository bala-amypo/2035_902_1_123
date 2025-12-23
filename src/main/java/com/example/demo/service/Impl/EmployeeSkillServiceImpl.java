package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    private static final Set<String> VALID_PROFICIENCY =
            Set.of("Beginner", "Intermediate", "Advanced", "Expert");

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {

        if (mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years");
        }

        if (!VALID_PROFICIENCY.contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        Employee employee = employeeRepository.findById(
                mapping.getEmployee().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (!employee.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }

        Skill skill = skillRepository.findById(
                mapping.getSkill().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));

        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }

        mapping.setActive(true);
        mapping.setEmployee(employee);
        mapping.setSkill(skill);

        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("EmployeeSkill not found"));

        if (mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years");
        }

        if (!VALID_PROFICIENCY.contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());

        return employeeSkillRepository.save(existing);
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
        EmployeeSkill skill = employeeSkillRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("EmployeeSkill not found"));

        skill.setActive(false);
        employeeSkillRepository.save(skill);
    }
}
