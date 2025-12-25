package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private boolean active;

    // Constructors
    public EmployeeSkill() {}

    public EmployeeSkill(Long employeeId, Skill skill, boolean active) {
        this.employeeId = employeeId;
        this.skill = skill;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
