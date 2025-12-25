@Override
public void deactivateEmployee(Long id) { // Ensure it is 'Long' not 'long'
    Employee employee = getEmployeeById(id);
    [cite_start]employee.setActive(false); [cite: 245]
    [cite_start]employeeRepository.save(employee); [cite: 243]
}