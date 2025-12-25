package com.example.demo.service;

import com.example.demo.model.Skill;

public interface SkillService {

    Skill createSkill(Skill skill);

    Skill updateSkill(long id, Skill skill);

    void deactivateSkill(long id);
}
