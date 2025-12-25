@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee create(Employee employee) {
        return repo.save(employee);
    }

    public Employee update(Long id, Employee employee) {
        employee.setId(id);
        return repo.save(employee);
    }

    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public void deactivate(Long id) {
        Employee emp = getById(id);
        emp.setActive(false);
        repo.save(emp);
    }
}
