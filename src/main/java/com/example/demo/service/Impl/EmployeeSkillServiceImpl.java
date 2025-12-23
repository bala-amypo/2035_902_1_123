package com.example.demo.serviceimpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository repository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeSkill create(EmployeeSkill employeeSkill) {
        return repository.save(employeeSkill);
    }

    @Override
    public EmployeeSkill update(Long id, EmployeeSkill employeeSkill) {
        EmployeeSkill existing = getById(id);
        existing.setEmployee(employeeSkill.getEmployee());
        existing.setSkill(employeeSkill.getSkill());
        existing.setProficiencyLevel(employeeSkill.getProficiencyLevel());
        existing.setYearsOfExperience(employeeSkill.getYearsOfExperience());
        return repository.save(existing);
    }

    @Override
    public EmployeeSkill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("EmployeeSkill not found"));
    }

    @Override
    public List<EmployeeSkill> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        EmployeeSkill employeeSkill = getById(id);
        employeeSkill.setActive(false);
        repository.save(employeeSkill);
    }
}
