package com.example.plantool.services;

import com.example.plantool.model.Skill;
import com.example.plantool.repository.SkillRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class SkillService {
    SkillRepo skillRepo = new SkillRepo();

    public boolean doesSkillExist(String skillName){

        for(int i = 0; i < skillRepo.findAllSkills().size(); i++){
            if(skillRepo.findAllSkills().get(i).equals(skillName)){
                return true;
            }
        }
        return false;
    }

    public boolean memberHasSkill(int memberId, int skillId){

        for(int i = 0; i < skillRepo.findMemberSkills(memberId).size(); i++){
            if(skillRepo.findMemberSkills(memberId).get(i).equals(skillId)){
                return true;
            }
        }
        return false;
    }

    public boolean projectHasSkill(int projectId, int skillId){
        for(int i = 0; i < skillRepo.findProjectSkills(projectId).size(); i++){
            if(skillRepo.findProjectSkills(projectId).get(i).equals(skillId)){
                return true;
            }
        }
        return false;
    }

    public void createSkill(String skillName){

        if (!doesSkillExist(skillName)){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.insertSkillToDB(skillName);
        }
    }

    public void assignSkillToMember(int memberId, int skillId){
        if (memberHasSkill(memberId, skillId)){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.assignSkillToMember(memberId, skillId);
        }
    }

    public void assignSkillToProject(int projectId, int skillId){
        if (projectHasSkill(projectId, skillId)){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.assignSkillToProject(projectId, skillId);
        }
    }

    public ArrayList<Skill> fetchAllSkills(){
        return skillRepo.findAllSkills();
    }

    public Skill fetchSkillByName(String skillName) throws SQLException {
        return skillRepo.findSkillByName(skillName);
    }

    public ArrayList<Skill> fetchMemberSkills(int memberId) throws SQLException {

        ArrayList<Skill> memberSkills = skillRepo.findMemberSkills(memberId);

        return memberSkills;

    }



}
