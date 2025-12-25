package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {

    Employee create(Employee employee);

    Employee update(Long id, Employee employee);

    Employee getById(Long id);

    List<Employee> getAll();

    void deactivate(Long id);
}
