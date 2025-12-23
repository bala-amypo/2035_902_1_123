public interface SkillService {
    Skill create(Skill skill);
    Skill update(Long id, Skill skill);
    Skill getById(Long id);
    List<Skill> getAll();
    void deactivate(Long id);
}
