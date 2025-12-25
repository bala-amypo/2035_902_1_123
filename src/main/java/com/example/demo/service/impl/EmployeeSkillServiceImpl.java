package com.example.demo.service.impl;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    @Override
    public EmployeeSkill create(EmployeeSkill employeeSkill) {
        // TODO: Add implementation to save employeeSkill
        return null;
    }

    @Override
    public EmployeeSkill update(Long id, EmployeeSkill employeeSkill) {
        // TODO: Add implementation to update employeeSkill by id
        return null;
    }

    @Override
    public EmployeeSkill getById(Long id) {
        // TODO: Add implementation to fetch employeeSkill by id
        return null;
    }

    @Override
    public List<EmployeeSkill> getAll() {
        // TODO: Add implementation to fetch all employeeSkills
        return null;
    }

    @Override
    public void deactivate(Long id) {
        // TODO: Add implementation to deactivate employeeSkill by id
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(long skillId) {
        // TODO: Add implementation to fetch employees by skill
        return null;
    }
}
