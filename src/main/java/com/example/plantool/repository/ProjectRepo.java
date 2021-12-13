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

    public void updateProjectName(int projectid, String projectname){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE project SET projectname='"+projectname+"' WHERE projectid="+projectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProjectStartDate(int projectid, LocalDate startdate){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE project SET projectstartdate='"+startdate+"' WHERE projectid="+projectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateProjectEndDate(int projectid, LocalDate enddate){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE project SET projectenddate='"+enddate+"' WHERE projectid="+projectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProjectDeadline(int projectid, LocalDate deadline){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE project SET projectdeadline='"+deadline+"' WHERE projectid="+projectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateHoursAllocated(int projectid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE project SET projecthoursallo='"+hours+"' WHERE projectid="+projectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateHoursUsed(int projectid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE project SET projecthoursused='"+hours+"' WHERE projectid="+projectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateDescription(int projectid, String description){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE project SET projectdescrip='"+description+"' WHERE projectid="+projectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

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
            System.out.println("Project Insert complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int fetchSingleProjectId(String projectName){
        int id = 0;
        try{
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT projectid FROM plantool.project WHERE projectname = '" + projectName + "'");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                id = resultSet.getInt(1);
                System.out.println("project repo id: " + id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    // hent enkelt projekt
    public Project fetchSingleProject(int projectid){
        Project tmpProject = new Project();
        try{
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
            "SELECT project.projectid, projectname, projectstartdate, projectenddate, projectdeadline, projecthoursallo, projecthoursused, projectleader, projectdescrip FROM project WHERE projectid="+projectid+"");
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

    // returnerer liste med deltagerid i et bestemt projekt
    public ArrayList<Integer> membersInProject(int projectId){
        ArrayList<Integer> projectMembers = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT assignment.userid FROM assignment WHERE projectid =" + projectId + "");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                projectMembers.add(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return projectMembers;
    }


    // returnerer liste med deltagere i et bestemt projekt
    public ArrayList<Member> listMembersInProject(int projectId){
        ArrayList<Member> projectMembers = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT assignment.projectid, assignment.userid, name, email FROM assignment JOIN user ON user.userid = assignment.userid WHERE assignment.projectid="+projectId+"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Member tmpMember = new Member();
                tmpMember.setMemberId(resultSet.getInt(2));
                tmpMember.setName(resultSet.getString(3));
                tmpMember.setEmail(resultSet.getString(4));
                projectMembers.add(tmpMember);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return projectMembers;
    }

    // knytter projektdeltager til bestemt projekt
    public void assignMemberToProject(int projectId, int memberId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO assignment(projectid, userid) VALUES(?,?) ");
            stmt.setInt(1, projectId);
            stmt.setInt(2, memberId);
            stmt.executeUpdate();
            System.out.println("Project Member Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    // slet project
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

    // returnerer liste med alle projekter
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
                tmpProject.setAssignees(listMembersInProject(tmpProject.getId()));
                allProjects.add(tmpProject);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return allProjects;
    }
}
