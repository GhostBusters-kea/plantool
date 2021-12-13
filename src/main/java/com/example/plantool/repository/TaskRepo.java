package com.example.plantool.repository;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.model.Task;
import com.example.plantool.utility.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskRepo {

    // opdater navn
    public void updateTaskName(int taskid, String taskname){
        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE task SET taskename='"+taskname+"' WHERE taskid="+taskid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // opdate startdato
    public void updateTaskStartDate(int taskid, LocalDate startdate){
        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE task SET taskstartdate='"+startdate+"' WHERE taskid="+taskid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // opdate slutdato
    public void updateTaskEndDate(int taskid, LocalDate enddate){
        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE task SET taskenddate='"+enddate+"' WHERE taskid="+taskid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // opdater deadline
    public void updateTaskDeadline(int taskid, LocalDate deadline){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE task SET taskdeadline='"+deadline+"' WHERE taskid="+taskid+"");

            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // opdater allokeret tid
    public void updateHoursAllocated(int taskid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE task SET taskhoursallo='"+hours+"' WHERE taskid="+taskid+"");
            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // opdater tid brugt
    public void updateHoursUsed(int taskid, int hours){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE task SET taskhoursused='"+hours+"' WHERE taskid="+taskid+"");
            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // opdater beskrivelse
    public void updateDescription(int taskid, String description){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement
                            ("UPDATE task SET taskdescription='"+description+"' WHERE taskid="+taskid+"");
            stmt.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void writeTaskToDB(Task task, int subprojectId){
        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement(
                            "INSERT INTO task(subprojectid, taskename, taskstartdate, taskenddate, taskdeadline, " +
                                    "taskhoursallo, taskhoursused, taskdescription) VALUES(?,?,?,?,?,?,?,?)"
                    );
            stmt.setInt(1, subprojectId);
            stmt.setString(2, task.getName());
            stmt.setObject(3, task.getStartDate());
            stmt.setObject(4, task.getEndDate());
            stmt.setObject(5, task.getDeadline());
            stmt.setInt(6, task.getHoursAllocated());
            stmt.setInt(7, task.getHoursUsed());
            stmt.setString(8, task.getProjectDescription());
            stmt.executeUpdate();
            System.out.println("Task Insert complete");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // hent enkelt task
    public Task fetchSingleTask(int taskid){
        Task tmpTask = new Task();
        try{
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT task.taskid, subprojectid, taskename, taskstartdate, taskenddate, taskdeadline, taskhoursallo, taskhoursused, taskdescription FROM task WHERE taskid="+taskid+"");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                tmpTask.setId(resultSet.getInt(1));
                tmpTask.setSubprojectId(resultSet.getInt(2));
                tmpTask.setName(resultSet.getString(3));
                tmpTask.setStartDate(resultSet.getDate(4).toLocalDate());
                tmpTask.setEndDate(resultSet.getDate(5).toLocalDate());
                tmpTask.setDeadline(resultSet.getDate(6).toLocalDate());
                tmpTask.setHoursAllocated(resultSet.getInt(7));
                tmpTask.setHoursUsed(resultSet.getInt(8));
                tmpTask.setProjectDescription(resultSet.getString(9));
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return tmpTask;
    }

    // returnerer liste med deltagerid i en bestemt task
    public ArrayList<Integer> membersInTask(int taskId){
        ArrayList<Integer> taskMembers = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT assignment.userid FROM assignment WHERE taskid =" + taskId + "");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                taskMembers.add(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return taskMembers;
    }

    // returnerer liste med deltagere i en bestemt task
    public ArrayList<Member> listMembersInTask(int taskId){
        ArrayList<Member> projectMembers = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT assignment.taskid, assignment.userid, name, email FROM assignment JOIN user ON user.userid = assignment.userid WHERE assignment.taskid="+taskId+"");

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

    // knytter projektdeltager til bestemt task
    public void assignMemberToTask(int taskId, int memberId){

        try{
            PreparedStatement stmt =
                    DatabaseConnector.getConnection().prepareStatement("INSERT INTO assignment(taskid, userid) VALUES(?,?) ");
            stmt.setInt(1, taskId);
            stmt.setInt(2, memberId);
            stmt.executeUpdate();
            System.out.println("Task Member Insert complete");
        } catch (SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    // slet task
    public void deleteTask(int taskId){

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "DELETE FROM task WHERE taskid="+taskId+"");
            stmt.executeUpdate();
            System.out.println("Delete complete");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    // returnerer liste med alle tasks
    public ArrayList<Task> fetchAllTasks(int subprojectId){

        ArrayList<Task> allTasks = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM task where subprojectid =" + subprojectId +"");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Task tmpTask = new Task();
                tmpTask.setId(resultSet.getInt(1));
                tmpTask.setSubprojectId(resultSet.getInt(2));
                tmpTask.setName(resultSet.getString(3));
                tmpTask.setStartDate(resultSet.getDate(4).toLocalDate());
                tmpTask.setEndDate(resultSet.getDate(5).toLocalDate());
                tmpTask.setDeadline(resultSet.getDate(6).toLocalDate());
                tmpTask.setHoursAllocated(resultSet.getInt(7));
                tmpTask.setHoursUsed(resultSet.getInt(8));
                tmpTask.setProjectDescription(resultSet.getString(9));
                allTasks.add(tmpTask);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return allTasks;
    }
}
