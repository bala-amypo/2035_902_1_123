package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Exact constructor required for injection in tests [cite: 61]
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository; [cite: 15, 61]
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // Enforce unique email logic is typically handled by DB unique constraint or pre-check
        employee.setActive(true);
        return employeeRepository.save(employee); [cite: 191]
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee existing = getEmployeeById(id); [cite: 212]
        existing.setFullName(employeeDetails.getFullName()); [cite: 218]
        existing.setEmail(employeeDetails.getEmail()); [cite: 219]
        return employeeRepository.save(existing); [cite: 213]
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(); [cite: 233]
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee employee = getEmployeeById(id); [cite: 242]
        employee.setActive(false); [cite: 245]
        employeeRepository.save(employee); [cite: 243]
    }
}