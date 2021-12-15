package com.example.plantool.services;


import com.example.plantool.model.Task;
import com.example.plantool.repository.TaskRepo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Author: Michael Dyvad
 *
 * Subtask service for funktionality
 */

public class TaskService {
    TaskRepo repo = new TaskRepo();

    //Update task name
    public void updateTaskName(int taskid, String name){
        repo.updateTaskName(taskid, name);
    }

    //Update task startdate
    public void updateTaskStartDate(int taskid, LocalDate startdate){
        repo.updateTaskStartDate(taskid,startdate);
    }

    //Update task end date
    public void updateTaskEndDate(int taskid,LocalDate enddate){
        repo.updateTaskEndDate(taskid,enddate);
    }

    //Update task dead line
    public void updateDeadline(int taskid, LocalDate deadline){
        repo.updateTaskDeadline(taskid,deadline);
    }

    //Update task hours allocated
    public void updateHoursAllocated(int taskid, int hours){
        repo.updateHoursAllocated(taskid,hours);
    }

    //Update task update hours used
    public void updateHoursUsed(int taskid, int hours){
        repo.updateHoursUsed(taskid,hours);
    }

    //Update task description
    public void updateTaskDescription(int taskid, String description){
        repo.updateDescription(taskid,description);
    }

    //Delets task
    public void deleteTask(int taskId){
        repo.deleteTask(taskId);
    }

    // Create a new task
    public Task createNewTask(int subprojectId, String taskName, LocalDate startDate, LocalDate endDate, LocalDate deadline,
                                    int hoursAllocated, String description){
        Task task = new Task();
        task.setSubprojectId(subprojectId);
        task.setName(taskName);
        task.setStartDate(startDate);
        task.setStartDate(endDate);
        task.setDeadline(deadline);
        task.setHoursAllocated(hoursAllocated);
        task.setProjectDescription(description);

        return task;

    }

    //Adds a task to database
    public void addTaskToDb(Task task, int subprojectId){
        repo.writeTaskToDB(task, subprojectId);
    }

    // fetch single task
    public Task fetchSingleTask(int taskId){
        return repo.fetchSingleTask(taskId);
    }

    // fetch all task
    public ArrayList<Task> fetchAllTasks(int subprojectId){
        return repo.fetchAllTasks(subprojectId);
    }


    // assign meber to task
    public void assignMemberToTask(int taskId, int memberId){

        if (taskHasMember(taskId, memberId)){
            System.out.println("Member already assigned to project");
        }

        else {repo.assignMemberToTask(taskId, memberId);
        }
    }

    public ArrayList<Integer> membersInTask(int taskId){
        return repo.membersInTask(taskId);
    }


    //Checks if task has a member
    public boolean taskHasMember(int taskId, int memberId){

        for(int i = 0; i < repo.membersInTask(taskId).size(); i++){
            if(repo.membersInTask(taskId).get(i).equals(memberId)){
                return true;
            }
        }
        return false;
    }

    // Method to calculate business days
    public static long calculateBusinessDays(Task task){
        int first = task.getStartDate().getDayOfWeek().getValue();
        int last = task.getEndDate().getDayOfWeek().getValue();

        long totalDays =
                ChronoUnit.DAYS.between(task.getStartDate(),task.getEndDate());

        long result = totalDays - 2*(totalDays/7);

        if(totalDays % 7 !=0){
            if(first==7) {
                result -= 1;
            } else if (last==7) {
                result -= 1;
            } else if (last<first){
                result -=2;
            }
        }
        return result;
    }
}
