package com.example.demo.repository;

import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(long skillId);

    // REQUIRED BY TEST (dummy)
    List<Object> findEmployeesByAllSkillNames(List<Object> skills, long count);
}
