package com.example.taskapp.dao;

import com.example.taskapp.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDAO extends JpaRepository<Skill,Long> {
    Skill findBySkill(String skill);
}