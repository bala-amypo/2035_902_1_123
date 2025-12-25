package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    // ✅ Constructor Injection (TEST REQUIREMENT)
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee create(Employee employee) {
        repository.findByEmail(employee.getEmail())
                .ifPresent(e -> {
                    throw new IllegalArgumentException("Email already exists");
                });
        return repository.save(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee existing = getById(id);

        existing.setFullName(employee.getFullName());
        existing.setDepartment(employee.getDepartment());
        existing.setJobTitle(employee.getJobTitle());

        return repository.save(existing);
    }

    @Override
    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        Employee emp = getById(id);
        emp.setActive(false);
        repository.save(emp);
    }
}
