package com.example.plantool.repository;

import com.example.plantool.model.Project;
import com.example.plantool.utility.DatabaseConnector;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



public class ProjectRepo {


    public void writeProjectToDB(Project project){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement(
                            "INSERT INTO project(projectname, projectstartdate, projectenddate, projectdeadline, projecthoursallo, projecthoursused) VALUES(?,?,?,?,?,?)"
                    );
            stmt.setString(1, project.getName());
            stmt.setObject(2, project.getStartDate());
            stmt.setObject(3, project.getEndDate());
            stmt.setObject(4, project.getDeadline());
            stmt.setInt(5, project.getHoursAllocated());
            stmt.setInt(6, project.getHoursUsed());
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
            "SELECT project.projectname, projectstartdate, projectenddate, projectdeadline, projecthoursallo FROM project WHERE projectid="+projectid+"");


            ResultSet resultset = stmt.executeQuery();

            while (resultset.next()){
                tmpProject.setName(resultset.getString(1));
                tmpProject.setStartDate(resultset.getDate(2).toLocalDate());
                tmpProject.setEndDate(resultset.getDate(3).toLocalDate());
                tmpProject.setDeadline(resultset.getDate(4).toLocalDate());
                tmpProject.setHoursAllocated(resultset.getInt(5));
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

        return tmpProject;

    }

    public void deleteProject(int projectid){

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "DELETE FROM project WHERE projectid="+projectid+"");
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

                tmpProject.setName(resultSet.getString(2));
                tmpProject.setStartDate(resultSet.getDate(3).toLocalDate());
                tmpProject.setEndDate(resultSet.getDate(4).toLocalDate());
                tmpProject.setDeadline(resultSet.getDate(5).toLocalDate());
                tmpProject.setHoursAllocated(resultSet.getInt(6));

                allProjects.add(tmpProject);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return allProjects;
    }
}
