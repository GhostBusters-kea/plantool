package com.example.plantool.repository;

import com.example.plantool.model.Project;
import com.example.plantool.utility.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectRepository {
    public static void main(String[] args) {
        ProjectRepository re = new ProjectRepository();

        System.out.println(re.fetchSingleProject(3));


    }


    public void writeProjectToDB(Project project) {


    }


    public Project fetchSingleProject(int projectId)  {
        Project tmp = new Project(0,null,null,null,null,null,0,0,null);

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement
                    ("SELECT projectname, projectstartdate, projectenddate, projectdeadline FROM project WERE projectid=" + projectId + "");

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                tmp.setId(resultSet.getInt(1));
                tmp.setName(resultSet.getString(2));
                tmp.setStartDate(resultSet.getDate(3));
                tmp.setEndDate(resultSet.getDate(4));
                tmp.setDeadline(resultSet.getDate(5));
            }
            return tmp;


        } catch (SQLException e){
            System.out.println();
        }

        return tmp;

    }

    public ArrayList<Project> fetchMultipleProjects(){
        return null;

    }

    public void updateProject(){}


    public void deleteProject(){}



}
