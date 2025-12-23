package com.example.demo.serviceimpl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee update(Long id, Employee employee) {
        Employee e = getById(id);
        e.setFullName(employee.getFullName());
        e.setDepartment(employee.getDepartment());
        e.setJobTitle(employee.getJobTitle());
        return repository.save(e);
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public void deactivate(Long id) {
        Employee e = getById(id);
        e.setActive(false);
        repository.save(e);
    }
}
