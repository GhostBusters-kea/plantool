package com.example.plantool.repository;

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

    public ArrayList<String> findAllSkills (){

        ArrayList<String> skills = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT * FROM skills");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                String tmp = result.getString(2);
                skills.add(tmp);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return skills;
    }

    public String findSkill(int skillId) throws SQLException {

        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT skills.skillname, FROM skills WHERE skillid ='" + skillId + "'");
        String skillName = "";

        ResultSet set = stmt.executeQuery();
        while(set.next()){
            skillName = set.getString(2);

        }
        return skillName;
    }

    public ArrayList<Integer> findMemberSkills(int id){

        ArrayList<Integer> memberSkills = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT userskills.skillid, FROM userskills WHERE userid ='" + id + "'");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                int tmpSkillId = result.getInt(2);

                memberSkills.add(tmpSkillId);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return memberSkills;
    }

    public ArrayList<Integer> findProjectSkills(int id){

        ArrayList<Integer> projectSkills = new ArrayList<>();

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("SELECT projectskills.skillid, FROM projectskills WHERE projectid ='" + id + "'");

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                int tmpSkillId = result.getInt(2);

                projectSkills.add(tmpSkillId);

            }
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        return projectSkills;
    }
}
