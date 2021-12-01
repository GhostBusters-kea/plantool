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

        Date date = new java.sql.Date(2021-1-12);

        Time time = new Time(2021-1-11);

        Project project = new Project();
        project.setName("Timetest");
        project.setStartDate(time);
        project.setEndDate(time);
        project.setDeadline(time);

        repo.writeProjectToDB(project);

    }

    public void writeProjectToDB(Project project){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement(
                            "INSERT INTO project(projectname, projectstartdate, projectenddate, projectdeadline) VALUES(?,?,?,?)"
                    );

            java.sql.Date sqldate= new java.sql.Date(project.getStartDate().getTime())

            stmt.setString(1, project.getName());
            stmt.setTime(2, project.getStartDate());
            stmt.setTime(3, project.getEndDate());
            stmt.setTime(4, project.getDeadline());
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
                tmpProject.setStartDate(resultset.getTime(2));
                tmpProject.setEndDate(resultset.getTime(3));
                tmpProject.setDeadline(resultset.getTime(4));
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
