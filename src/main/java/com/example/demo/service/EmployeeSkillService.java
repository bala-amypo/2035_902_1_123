package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;

import java.util.List;

public interface EmployeeSkillService {

    EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill);

    void deactivateEmployeeSkill(long id);

    List<EmployeeSkill> getSkillsForEmployee(long employeeId);

    List<EmployeeSkill> getEmployeesBySkill(long skillId);
}
