package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> skills;

    private Long employeeId;

    private boolean active;

    // Constructors
    public SearchQueryRecord() {}

    public SearchQueryRecord(List<String> skills, Long employeeId, boolean active) {
        this.skills = skills;
        this.employeeId = employeeId;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
S