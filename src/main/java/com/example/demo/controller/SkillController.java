@PutMapping("/{id}/deactivate")
public void deactivate(@PathVariable Long id) {
    service.deactivateSkill(id); // Matches service method
}