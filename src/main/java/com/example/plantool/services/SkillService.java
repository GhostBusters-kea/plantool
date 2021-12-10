package com.example.plantool.services;

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


        if (doesSkillExist(skillName) == false){
            System.out.println("Skill already exists");
        }

        else {
            skillRepo.insertSkillToDB(skillName);
        }
    }

    public void assignSkillToMember(int memberId, int skillId){



    }

    public void assignSkillToProject(int projectId, int skillId){



    }

    public ArrayList<String> fetchAllSkills(){
        return skillRepo.findAllSkills();
    }

    public ArrayList<String> fetchMemberSkills(int memberId) throws SQLException {

        ArrayList<String> memberSkills = new ArrayList<>();
        ArrayList<Integer> memberSkillsById = skillRepo.findMemberSkills(memberId);

        for(int i = 0; i < memberSkillsById.size(); i++) {
            memberSkills.add(skillRepo.findSkill(memberSkillsById.get(i)));
        }
        return memberSkills;

    }



}
