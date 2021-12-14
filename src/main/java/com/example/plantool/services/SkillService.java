package com.example.plantool.services;

import com.example.plantool.model.Skill;
import com.example.plantool.repository.SkillRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class SkillService {
    SkillRepo skillRepo = new SkillRepo();

    // checker om kompetencen allerede findes i databassen
    public boolean doesSkillExist(String skillName){
        for(int i = 0; i < skillRepo.findAllSkills().size(); i++){
            if(skillRepo.findAllSkills().get(i).getSkillName().equals(skillName)){
                return true;
            }
        }
        return false;
    }

    // checker om projektdeltager allerede har en bestemt kompetence
    public boolean memberHasSkill(int memberId, int skillId){
        for(int i = 0; i < skillRepo.findMemberSkills(memberId).size(); i++){
            if(skillRepo.findMemberSkills(memberId).get(i).getSkillId()==skillId){
                return true;
            }
        }
        return false;
    }

    // checker om et project allerede er tildelt en bestemt kompetence
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

    // opret ny kompetence
    public void createSkill(String skillName){

        if (doesSkillExist(skillName)){
            System.out.println("Skill already exists");
        }
        else {
            skillRepo.insertSkillToDB(skillName);
        }
    }

    // knytter kompetence til projektdeltager
    public void assignSkillToMember(int memberId, int skillId){

        if (memberHasSkill(memberId, skillId)){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.assignSkillToMember(memberId, skillId);
        }
    }


    // knytter kompetence til project
    public void assignSkillToProject(int projectId, int skillId){
        if (projectHasSkill(projectId, skillId)){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.assignSkillToProject(projectId, skillId);
        }
    }

    // returnere en liste med alle kompetencer
    public ArrayList<Skill> fetchAllSkills(){
        return skillRepo.findAllSkills();
    }

    // returnere bestemt kompetence på navn
    public Skill fetchSkillByName(String skillName) {
        return skillRepo.findSkillByName(skillName);
    }

    // returnere liste med kompetencerne på en bestemt projektdeltager
    public ArrayList<Skill> fetchMemberSkills(int memberId) {
        ArrayList<Skill> memberSkills = skillRepo.findMemberSkills(memberId);
        return memberSkills;
    }

}
