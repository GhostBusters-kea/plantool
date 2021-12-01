package com.example.plantool.repository;

import com.example.plantool.model.Project;
import com.example.plantool.utility.DatabaseConnector;
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;


public class ProjectRepo {

    public static void main(String[] args) {
        ProjectRepo repo = new ProjectRepo();




        Project project = new Project();
        project.setName("Timetest");
        project.setStartDate(date);
        project.setEndDate(date);
        project.setDeadline(date);

        repo.writeProjectToDB(project);

    }

    public void writeProjectToDB(Project project){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement(
                            "INSERT INTO project(projectname, projectstartdate, projectenddate, projectdeadline) VALUES(?,?,?,?)"
                    );

            stmt.setString(1, project.getName());
            stmt.setDate(2, new java.sql.Date(project.getStartDate());
            stmt.setDate(3, project.getEndDate());
            stmt.setDate(4, project.getDeadline());
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
            "SELECT project.projectname, projectstartdate, projectenddate, projectdeadline FROM project WHERE projectid="+projectid+"");


            ResultSet resultset = stmt.executeQuery();

            while (resultset.next()){

                tmpProject.setName(resultset.getString(1));
                tmpProject.setStartDate(resultset.getDate(2));
                tmpProject.setEndDate(resultset.getDate(3));
                tmpProject.setDeadline(resultset.getDate(4));
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

        return tmpProject;

    }

    public void deleteProject(int projectid){

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "DELETE FROM project WHERE projectid="+projectid+""
            );
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
                tmpProject.setStartDate(resultSet.getDate(3));
                tmpProject.setEndDate(resultSet.getDate(4));
                tmpProject.setDeadline(resultSet.getDate(5));

                allProjects.add(tmpProject);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return allProjects;

    }

}
