package com.example.plantool.services;

import com.example.plantool.model.Skill;
import com.example.plantool.repository.SkillRepo;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Author: Jonas Munk
 *
 * Skill service for funktionality
 */
public class SkillService {
    SkillRepo skillRepo = new SkillRepo();

    // checks if the competence already exists in the database
    public boolean doesSkillExist(String skillName){
        for(int i = 0; i < skillRepo.findAllSkills().size(); i++){
            if(skillRepo.findAllSkills().get(i).getSkillName().equals(skillName)){
                return true;
            }
        }
        return false;
    }

    // checks if the project participant already has a certain competence
    public boolean memberHasSkill(int memberId, int skillId){
        for(int i = 0; i < skillRepo.findMemberSkills(memberId).size(); i++){
            if(skillRepo.findMemberSkills(memberId).get(i).getSkillId()==skillId){
                return true;
            }
        }
        return false;
    }

    // checks whether a project has already been assigned a specific competency
    public boolean projectHasSkill(int projectId, int skillId){
        for(int i = 0; i < skillRepo.findProjectSkills(projectId).size(); i++){
            if(skillRepo.findProjectSkills(projectId).get(i).getSkillId()==skillId){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Skill> skillsInProject(int projectId){
        return skillRepo.findProjectSkills(projectId);
    }

    // Create new skill
    public void createSkill(String skillName){

        if (doesSkillExist(skillName)){
            System.out.println("Skill already exists");
        }
        else {
            skillRepo.insertSkillToDB(skillName);
        }
    }

    // Assign skill to a member
    public void assignSkillToMember(int memberId, int skillId){

        if (memberHasSkill(memberId, skillId)){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.assignSkillToMember(memberId, skillId);
        }
    }


    // Assign skill to project
    public void assignSkillToProject(int projectId, int skillId){
        if (projectHasSkill(projectId, skillId)){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.assignSkillToProject(projectId, skillId);
        }
    }

    // Returns a list with all skills
    public ArrayList<Skill> fetchAllSkills(){
        return skillRepo.findAllSkills();
    }

    // Return skill on name
    public Skill fetchSkillByName(String skillName) {
        return skillRepo.findSkillByName(skillName);
    }

    // return list of competencies of a particular project participant
    public ArrayList<Skill> fetchMemberSkills(int memberId) {
        ArrayList<Skill> memberSkills = skillRepo.findMemberSkills(memberId);
        return memberSkills;
    }

}
