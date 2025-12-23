package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository EmployeeRepository;

    // IMPORTANT: constructor order must match tests
    public EmployeeServiceImpl(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeRepository.findByEmail(employee.getEmail())
                .ifPresent(e -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        employee.setActive(true);
        return EmployeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);

        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setJobTitle(employee.getJobTitle());

        return EmployeeRepository.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return EmployeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return EmployeeRepository.findByActiveTrue();
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setActive(false);
        EmployeeRepository.save(employee);
    }
}
