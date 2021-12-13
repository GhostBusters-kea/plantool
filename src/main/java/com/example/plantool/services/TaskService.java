package com.example.plantool.services;

import com.example.plantool.model.Project;
import com.example.plantool.model.Task;
import com.example.plantool.repository.TaskRepo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TaskService {
    TaskRepo repo = new TaskRepo();

    public void updateTaskName(int taskid, String name){
        repo.updateTaskName(taskid, name);
    }

    public void updateTaskStartDate(int taskid, LocalDate startdate){
        repo.updateTaskStartDate(taskid,startdate);
    }

    public void updateTaskEndDate(int taskid,LocalDate enddate){
        repo.updateTaskEndDate(taskid,enddate);
    }

    public void updateDeadline(int taskid, LocalDate deadline){
        repo.updateTaskDeadline(taskid,deadline);
    }

    public void updateHoursAllocated(int taskid, int hours){
        repo.updateHoursAllocated(taskid,hours);
    }

    public void updateHoursUsed(int taskid, int hours){
        repo.updateHoursUsed(taskid,hours);
    }

    public void updatesubDescription(int taskid, String description){
        repo.updateDescription(taskid,description);
    }

    // opret ny task
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

    public void addTaskToDb(Task task, int subprojectId){
        repo.writeTaskToDB(task, subprojectId);
    }

    // hent enkelt task
    public Task fetchSingleTask(int taskId){
        return repo.fetchSingleTask(taskId);
    }

    // hent alle tasks
    public ArrayList<Task> fetchAllTasks(int subprojectId){
        return repo.fetchAllTasks(subprojectId);
    }


    // knytter taskdeltager til bestemt task
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



    public boolean taskHasMember(int taskId, int memberId){

        for(int i = 0; i < repo.membersInTask(taskId).size(); i++){
            if(repo.membersInTask(taskId).get(i).equals(memberId)){
                return true;
            }
        }
        return false;
    }

    // metode til udregning af arbejdsdage
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
