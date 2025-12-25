package com.example.demo.repository;

import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    // Get all skills for an employee
    List<EmployeeSkill> findByEmployeeId(Long employeeId);

    // Get all active skills for an employee
    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);

    // Get skills by skill name (for a specific employee)
    List<EmployeeSkill> findByEmployeeIdAndSkillNameIn(Long employeeId, List<String> skillNames);
}
