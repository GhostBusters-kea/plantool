package com.example.plantool.repository;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.model.SubProject;
import com.example.plantool.utility.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Author: Jonas Munk
 *
 * Subproject repository data querie
 */
public class SubprojectsRepo {

    // Update subproject name
    public void updateSubProjectName(int subprojectid, String subprojectname){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subproject SET subprojectname='"+subprojectname+"' WHERE subprojectid="+subprojectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Update subproject start date
    public void updateSubProjectStartDate(int subprojectid, LocalDate startdate){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subproject SET subprojectstartdate='"+startdate+"' WHERE subprojectid="+subprojectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    // Update subproject end date - not implementet
    public void updateSubProjectEndDate(int subprojectid, LocalDate enddate){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subproject SET subprojectenddate='"+enddate+"' WHERE subprojectid="+subprojectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    // Update subproject deadline
    public void updateSubProjectDeadline(int subprojectid, LocalDate deadline){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subproject SET subprojectdeadline='"+deadline+"' WHERE subprojectid="+subprojectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Update subproject hours allocated
    public void updateHoursAllocated(int subprojectid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subproject SET subprojecthoursallo='"+hours+"' WHERE subprojectid="+subprojectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Update hours used - not implementet
    public void updateHoursUsed(int subprojectid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subproject SET subprojecthoursused='"+hours+"' WHERE subprojectid="+subprojectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Update subproject description
    public void updateDescription(int subprojectid, String description){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subproject SET subprojectdescription='"+description+"' WHERE subprojectid="+subprojectid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    // Creates subproject in database TODO: Change Project to projectId
    public void writeSubProjectToDB(Project project, int projectid){
        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement(
                            "INSERT INTO subproject(projectid, subprojectname, subprojectstartdate, subprojectenddate, subprojectdeadline, " +
                                    "subprojecthoursallo, subprojecthoursused, subprojectdescription) VALUES(?,?,?,?,?,?,?,?)"
                    );
            stmt.setInt(1, projectid);
            stmt.setString(2, project.getName());
            stmt.setObject(3, project.getStartDate());
            stmt.setObject(4, project.getEndDate());
            stmt.setObject(5, project.getDeadline());
            stmt.setInt(6, project.getHoursAllocated());
            stmt.setInt(7, project.getHoursUsed());
            stmt.setString(8, project.getProjectDescription());
            stmt.executeUpdate();
            System.out.println("Project Insert complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Fetch a single subproject
    public SubProject fetchSingleSubProject(int subprojectid){
        SubProject tmpProject = new SubProject();
        try{
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT subproject.subprojectid, subprojectname, subprojectstartdate, subprojectenddate, subprojectdeadline, subprojecthoursallo, subprojecthoursused, subprojectdescription FROM subproject WHERE subprojectid="+subprojectid+"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                tmpProject.setId(resultSet.getInt(1));
                tmpProject.setName(resultSet.getString(2));
                tmpProject.setStartDate(resultSet.getDate(3).toLocalDate());
                tmpProject.setEndDate(resultSet.getDate(4).toLocalDate());
                tmpProject.setDeadline(resultSet.getDate(5).toLocalDate());
                tmpProject.setHoursAllocated(resultSet.getInt(6));
                tmpProject.setHoursUsed(resultSet.getInt(7));
                tmpProject.setProjectDescription(resultSet.getString(8));
            }

            //tmpProject.setSkillsAllocated(fetchSkills(tmpProject.getId()));


        } catch (SQLException e){
            e.printStackTrace();
        }
        return tmpProject;
    }

        // Returns a list with members in subproject
    public ArrayList<Integer> membersInSubProject(int subprojectId){
        ArrayList<Integer> projectMembers = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT assignment.userid FROM assignment WHERE subprojectid =" + subprojectId + "");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                projectMembers.add(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return projectMembers;
    }



    // Assign a member to subproject
    public void assignMemberToSubProject(int subprojectId, int memberId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO assignment(subprojectid, userid) VALUES(?,?) ");
            stmt.setInt(1, subprojectId);
            stmt.setInt(2, memberId);
            stmt.executeUpdate();
            System.out.println("Project Member Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }


    // Deletes subproject
    public void deleteSubProject(int subprojectId){

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "DELETE FROM subproject WHERE subprojectid="+subprojectId+"");
            stmt.executeUpdate();
            System.out.println("Delete complete");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Returns a list with subprojects from project
    public ArrayList<SubProject> fetchSubProjectsFromProject(int projectid){

        ArrayList<SubProject> tmpsubprojects = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM subproject WHERE subproject.projectid="+projectid+"");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                SubProject tmpProject = new SubProject();
                tmpProject.setId(resultSet.getInt(1));
                tmpProject.setName(resultSet.getString(3));
                tmpProject.setStartDate(resultSet.getDate(4).toLocalDate());
                tmpProject.setEndDate(resultSet.getDate(5).toLocalDate());
                tmpProject.setDeadline(resultSet.getDate(6).toLocalDate());
                tmpProject.setHoursAllocated(resultSet.getInt(7));
                tmpProject.setHoursUsed(resultSet.getInt(8));
                tmpProject.setProjectDescription(resultSet.getString(9));
                tmpsubprojects.add(tmpProject);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return tmpsubprojects;
    }

}
