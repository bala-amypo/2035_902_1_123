package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee createEmployee(Employee e) {
        return repo.save(e);
    }

    public Employee updateEmployee(long id, Employee e) {
        e.setId(id);
        return repo.save(e);
    }

    public Employee getEmployeeById(long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public void deactivateEmployee(long id) {
        repo.findById(id).ifPresent(emp -> {
            emp.setActive(false);
            repo.save(emp);
        });
    }
}
