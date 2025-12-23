@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repository;

    public SkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }

    public Skill create(Skill skill) {
        return repository.save(skill);
    }

    public Skill update(Long id, Skill skill) {
        Skill s = getById(id);
        s.setName(skill.getName());
        s.setCategory(skill.getCategory());
        s.setDescription(skill.getDescription());
        return repository.save(s);
    }

    public Skill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public List<Skill> getAll() {
        return repository.findAll();
    }

    public void deactivate(Long id) {
        Skill s = getById(id);
        s.setActive(false);
        repository.save(s);
    }
}
