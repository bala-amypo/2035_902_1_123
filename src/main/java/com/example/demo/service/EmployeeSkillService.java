package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;
import java.util.List;

public interface EmployeeSkillService {

    EmployeeSkill create(EmployeeSkill employeeSkill);

    EmployeeSkill update(Long id, EmployeeSkill employeeSkill);

    EmployeeSkill getById(Long id);

    List<EmployeeSkill> getAll();

    void deactivate(Long id);

    List<EmployeeSkill> getEmployeesBySkill(long skillId);
}
