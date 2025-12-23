package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService service;

    public EmployeeSkillController(EmployeeSkillService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeSkill createEmployeeSkill(
            @RequestBody EmployeeSkill employeeSkill) {
        return service.create(employeeSkill);
    }

    @PutMapping("/{id}")
    public EmployeeSkill updateEmployeeSkill(
            @PathVariable Long id,
            @RequestBody EmployeeSkill employeeSkill) {
        return service.update(id, employeeSkill);
    }

    @GetMapping("/{id}")
    public EmployeeSkill getEmployeeSkill(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<EmployeeSkill> getAllEmployeeSkills() {
        return service.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateEmployeeSkill(@PathVariable Long id) {
        service.deactivate(id);
    }
}
