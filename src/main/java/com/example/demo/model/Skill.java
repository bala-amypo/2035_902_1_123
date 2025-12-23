package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String category;
    private String description;
    private Boolean active = true;

    @OneToMany(mappedBy = "skill")
    private List<EmployeeSkill> employees;

    // getters & setters
}
