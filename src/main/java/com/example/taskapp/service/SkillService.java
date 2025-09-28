package com.example.taskapp.service;

import com.example.taskapp.dao.SkillDAO;
import com.example.taskapp.exceptions.NotFoundException;
import com.example.taskapp.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillService {

    @Autowired
    SkillDAO skillDAO;

    public Skill createSkill(Skill skill){
        Skill existingSkill = skillDAO.findBySkill(skill.getSkill());
        if (existingSkill != null) {
            return existingSkill; // Return existing skill
        }
        return skillDAO.save(skill);
    }

    public Skill getSkillByName(String skill){
        Skill dbSkill=skillDAO.findBySkill(skill);
        if(dbSkill==null){
            throw new NotFoundException("No skill with name: "+skill);
        }
        return dbSkill;
    }

    public List<Skill> getAllSkills(){
        return skillDAO.findAll();
    }
}
