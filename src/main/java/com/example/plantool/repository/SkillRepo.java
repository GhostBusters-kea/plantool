package com.example.plantool.repository;

import com.example.plantool.model.Skill;
import com.example.plantool.utility.DatabaseConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkillRepo {

    public void insertSkillToDB (String skillName){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO skill(skillname) VALUES(?) ");
            stmt.setString(1, skillName);
            stmt.executeUpdate();
            System.out.println("Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void assignSkillToMember(int memberId, int skillId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO userskills(userid, skillid) VALUES(?,?) ");
            stmt.setInt(1, memberId);
            stmt.setInt(2, skillId);
            stmt.executeUpdate();
            System.out.println("Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void assignSkillToProject(int projectId, int skillId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO projectskills(projectid, skillid) VALUES(?,?) ");
            stmt.setInt(1, projectId);
            stmt.setInt(2, skillId);
            stmt.executeUpdate();
            System.out.println("Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public ArrayList<Skill> findAllSkills (){

        ArrayList<Skill> skills = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT * FROM skills");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                Skill tmpSkill = new Skill(result.getInt(1), result.getString(2));
                skills.add(tmpSkill);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return skills;
    }

    public Skill findSkillById(int skillId) throws SQLException {

        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT skills.skillid, skillname FROM skills WHERE skillid ='" + skillId + "'");
        Skill skill = new Skill();

        ResultSet set = stmt.executeQuery();
        while(set.next()){
            skill.setSkillId(set.getInt(1));
            skill.setSkillName(set.getString(2));

        }
        return skill;
    }

    public Skill findSkillByName(String skillName) throws SQLException {

        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT skills.skillid, skillname FROM skills WHERE skillname ='" + skillName + "'");
        Skill skill = new Skill();

        ResultSet set = stmt.executeQuery();
        while(set.next()){
            skill.setSkillId(set.getInt(1));
            skill.setSkillName(set.getString(2));

        }
        return skill;
    }

    public ArrayList<Skill> findMemberSkills(int memberId){

        ArrayList<Skill> memberSkills = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT userskills.skillid, skillname FROM userskills WHERE userid ='" + memberId + "'");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                Skill tmpSkill = new Skill(result.getInt(1), result.getString(2));
                memberSkills.add(tmpSkill);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return memberSkills;
    }

    public ArrayList<Skill> findProjectSkills(int projectId){

        ArrayList<Skill> projectSkills = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT projectskills.skillid, skillname FROM projectskills WHERE projectid ='" + projectId + "'");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                Skill tmpSkill = new Skill(result.getInt(1), result.getString(2));
                projectSkills.add(tmpSkill);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return projectSkills;
    }
}
