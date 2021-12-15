package com.example.plantool.repository;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.model.SubTask;
import com.example.plantool.model.Task;
import com.example.plantool.services.SubTaskService;
import com.example.plantool.utility.DatabaseConnector;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Author: Kevin Funch
 *
 * Subtask repository data querie
 */

public class SubTaskRepo {

    // Update hours allocated
    public void updateHoursAllocated(int subtaskid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subtask SET subtaskhoursallo='"+hours+"' WHERE subtaskid="+subtaskid+"");
            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Update hours used - not implementet
    public void updateHoursUsed(int subtaskid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE subtask SET subtaskhoursused='"+hours+"' WHERE subtaskid="+subtaskid+"");
            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Create subtask in database
    public void writeSubTaskToDB(SubTask subTask, int taskId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement(
                            "INSERT INTO subtask(taskid, subtaskname, subtaskstartdate, subtaskenddate, subtaskdeadline, " +
                                    "subtaskhoursallo, subtaskhoursused, subtaskdescription) VALUES(?,?,?,?,?,?,?,?)"
                    );

            stmt.setInt(1, taskId);
            stmt.setString(2, subTask.getName());
            stmt.setObject(3, subTask.getStartDate());
            stmt.setObject(4, subTask.getEndDate());
            stmt.setObject(5, subTask.getDeadline());
            stmt.setInt(6, subTask.getHoursAllocated());
            stmt.setInt(7, subTask.getHoursUsed());
            stmt.setString(8, subTask.getProjectDescription());
            stmt.executeUpdate();
            System.out.println("Project Insert complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Return a list with members in subtask
    public ArrayList<Integer> membersInSubTask(int subtaskId){
        ArrayList<Integer> subTaskMembers = new ArrayList<>();

        try {

            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT assignment.userid FROM assignment WHERE subtaskid ='" + subtaskId + "'");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                subTaskMembers.add(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return subTaskMembers;
    }

    //Assign members to a subtask
    public void assignMemberToSubTask(int subTaskId, int memberId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO assignment(subtaskid, userid) VALUES(?,?) ");
            stmt.setInt(1, subTaskId);
            stmt.setInt(2, memberId);
            stmt.executeUpdate();
            System.out.println("Project Members Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    // Deletes a subtask
    public void deleteSubTask(int subTaskId){

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "DELETE FROM project WHERE subtaskid="+subTaskId+"");
            stmt.executeUpdate();
            System.out.println("Delete complete");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    //Return a list with all sub task
    public ArrayList<SubTask> fetchAllSubTask(int taskid){

        ArrayList<SubTask> allSubTask = new ArrayList<>();

        try {

            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM subtask WHERE taskid ='" + taskid + "'");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                SubTask tmpSubTask = new SubTask();

                tmpSubTask.setId(resultSet.getInt(1));
                tmpSubTask.setTaskID(resultSet.getInt(2));
                tmpSubTask.setName(resultSet.getString(3));
                tmpSubTask.setStartDate(resultSet.getDate(4).toLocalDate());
                tmpSubTask.setEndDate(resultSet.getDate(5).toLocalDate());
                tmpSubTask.setDeadline(resultSet.getDate(6).toLocalDate());
                tmpSubTask.setHoursAllocated(resultSet.getInt(7));
                tmpSubTask.setHoursUsed(resultSet.getInt(8));
                tmpSubTask.setProjectDescription(resultSet.getString(9));

                allSubTask.add(tmpSubTask);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return allSubTask;
    }

}