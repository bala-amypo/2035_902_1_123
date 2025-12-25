@Override
public void deactivateEmployee(Long id) { // Change 'long' to 'Long'
    Employee employee = getEmployeeById(id);
    employee.setActive(false);
    employeeRepository.save(employee);
}