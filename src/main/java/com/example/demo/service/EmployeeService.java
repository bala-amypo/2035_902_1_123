package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee updateEmployee(long id, Employee employee);

    Employee getEmployeeById(long id);

    List<Employee> getAllEmployees();

    void deactivateEmployee(long id);
}
