package com.example.plantool.repository;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.utility.DatabaseConnector;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



public class ProjectRepo {

//    public void writeSkillToDB(String skill, int projectid){
//
//        try{
//            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("" +
//                    "INSERT INTO skill(skillname, projectid) VALUES(?,?)");
//
//            stmt.setString(1,skill);
//            stmt.setInt(2,projectid);
//            stmt.executeUpdate();
//            System.out.println("Insert complete");
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public static ArrayList<String> fetchSkills(int projectid){
//        ArrayList<String> skills = new ArrayList<>();
//
//        try {
//            PreparedStatement stmt =
//                    DatabaseConnector.getConnection().prepareStatement(
//                            "SELECT skill.skillname FROM skill WHERE projectid="+projectid+"");
//
//            ResultSet resultSet = stmt.executeQuery();
//
//            while (resultSet.next()){
//                skills.add(resultSet.getString(1));
//            }
//
//
//            } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return skills;
//    }

    public void writeProjectToDB(Project project){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement(
                            "INSERT INTO project(projectname, projectstartdate, projectenddate, projectdeadline, " +
                                    "projecthoursallo, projecthoursused, projectleader, projectdescrip) VALUES(?,?,?,?,?,?,?,?)"
                    );
            stmt.setString(1, project.getName());
            stmt.setObject(2, project.getStartDate());
            stmt.setObject(3, project.getEndDate());
            stmt.setObject(4, project.getDeadline());
            stmt.setInt(5, project.getHoursAllocated());
            stmt.setInt(6, project.getHoursUsed());
            stmt.setInt(7, project.getWhoIsLeader());
            stmt.setString(8, project.getProjectDescription());
            stmt.executeUpdate();
            System.out.println("Insert complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Project fetchSingleProject(int projectid){

        Project tmpProject = new Project();

        try{
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
            "SELECT project.projectid, projectname, projectstartdate, projectenddate, projectdeadline, projecthoursallo, projectleader, projectdescrip FROM project WHERE projectid="+projectid+"");


            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                tmpProject.setId(resultSet.getInt(1));
                tmpProject.setName(resultSet.getString(2));
                tmpProject.setStartDate(resultSet.getDate(3).toLocalDate());
                tmpProject.setEndDate(resultSet.getDate(4).toLocalDate());
                tmpProject.setDeadline(resultSet.getDate(5).toLocalDate());
                tmpProject.setHoursAllocated(resultSet.getInt(6));
                tmpProject.setHoursUsed(resultSet.getInt(7));
                tmpProject.setWhoIsLeader(resultSet.getInt(8));
                tmpProject.setProjectDescription(resultSet.getString(9));
            }

            //tmpProject.setSkillsAllocated(fetchSkills(tmpProject.getId()));


        } catch (SQLException e){
            e.printStackTrace();
        }

        return tmpProject;
    }

    public ArrayList<Integer> membersInProject(int projectId){
        ArrayList<Integer> projectMembers = new ArrayList<>();

        try {

            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT assignment.userid FROM assignment WHERE projectid ='" + projectId + "'");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                projectMembers.add(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return projectMembers;
    }

    public void assignMemberToProject(int projectId, int memberId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO assignment(projectid, userid) VALUES(?,?) ");
            stmt.setInt(1, projectId);
            stmt.setInt(2, memberId);
            stmt.executeUpdate();
            System.out.println("Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void deleteProject(int projectId){

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "DELETE FROM project WHERE projectid="+projectId+"");
            stmt.executeUpdate();
            System.out.println("Delete complete");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    // TODO: update project

    public ArrayList<Project> fetchAllProjects(){

        ArrayList<Project> allProjects = new ArrayList<>();

        try {

            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM project");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Project tmpProject = new Project();

                tmpProject.setId(resultSet.getInt(1));
                tmpProject.setName(resultSet.getString(2));
                tmpProject.setStartDate(resultSet.getDate(3).toLocalDate());
                tmpProject.setEndDate(resultSet.getDate(4).toLocalDate());
                tmpProject.setDeadline(resultSet.getDate(5).toLocalDate());
                tmpProject.setHoursAllocated(resultSet.getInt(6));
                tmpProject.setHoursUsed(resultSet.getInt(7));
                tmpProject.setWhoIsLeader(resultSet.getInt(8));
                tmpProject.setProjectDescription(resultSet.getString(9));

                allProjects.add(tmpProject);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return allProjects;
    }
}
