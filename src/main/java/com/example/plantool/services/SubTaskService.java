package com.example.plantool.services;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.model.SubTask;
import com.example.plantool.model.Task;
import com.example.plantool.repository.ProjectRepo;
import com.example.plantool.repository.SubTaskRepo;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubTaskService {
    SubTaskRepo repo = new SubTaskRepo();

    public void updateSubTaskName(int subtaskid, String name){
        repo.updateSubTaskName(subtaskid, name);
    }

    public void updateSubTaskStartDate(int subtaskid, LocalDate startdate){
        repo.updateSubTaskStartDate(subtaskid,startdate);
    }

    public void updateSubTaskEndDate(int subtaskid,LocalDate enddate){
        repo.updateSubTaskEndDate(subtaskid,enddate);
    }

    public void updateDeadline(int subtaskid, LocalDate deadline){
        repo.updateSubTaskDeadline(subtaskid,deadline);
    }

    public void updateHoursAllocated(int subtaskid, int hours){
        repo.updateHoursAllocated(subtaskid,hours);
    }

    public void updateHoursUsed(int subtaskid, int hours){
        repo.updateHoursUsed(subtaskid,hours);
    }

    public void updatesubDescription(int subtaskid, String description){
        repo.updateDescription(subtaskid,description);
    }


    // opret nyt projekt
    public SubTask createNewSubTak(String subTaskName, LocalDate startDate, LocalDate endDate, LocalDate deadline,
                                    int hoursAllocated, int whoIsLeader, String description){
        SubTask subTask = new SubTask();
        subTask.setName(subTaskName);
        subTask.setStartDate(startDate);
        subTask.setStartDate(endDate);
        subTask.setDeadline(deadline);
        subTask.setHoursAllocated(hoursAllocated);
        subTask.setWhoIsLeader(whoIsLeader);
        subTask.setProjectDescription(description);
        return subTask;
    }

    public void addSubTaskToDB(SubTask subTask, int taskid){
        repo.writeSubTaskToDB(subTask, taskid);
    }

    // hent enkelt projekt
    public SubTask fetchSingleSubTask(int subTaskID){
        return repo.fetchSingleSubTask(subTaskID);
    }

    // hent alle projekter
    public ArrayList<SubTask> fetchAllSubTask(int taskId){
        return repo.fetchAllSubTask(taskId);
    }

    public void assignMemberToSubTask(int subTaskId, int memberId){
        if (subTaskHasMember(subTaskId, memberId)){
            System.out.println("Member already assigned to project");
        }

        else {
            repo.assignMemberToSubTask(subTaskId, memberId);
        }
    }

    public ArrayList<Integer> membersInSubTask(int taskId){
        return repo.membersInSubTask(taskId);
    }



    public boolean subTaskHasMember(int subTaskId, int memberId){

        for(int i = 0; i < repo.membersInSubTask(subTaskId).size(); i++){
            if(repo.membersInSubTask(subTaskId).get(i).equals(memberId)){
                return true;
            }
        }
        return false;
    }

    // metode til udregning af arbejdsdage
    public static long calculateBusinessDays(SubTask subTask){
        int first = subTask.getStartDate().getDayOfWeek().getValue();
        int last = subTask.getEndDate().getDayOfWeek().getValue();

        long totalDays =
                ChronoUnit.DAYS.between(subTask.getStartDate(),subTask.getEndDate());

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

    // metode nummer 2  - kan ændres til at returnere en liste med alle dagene mellem start og end
    public static int countBusinessDays(SubTask subTask){

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;

        List<LocalDate> businessDays = subTask.getStartDate().datesUntil(subTask.getEndDate())
                .filter(isWeekend.negate())
                .collect(Collectors.toList());

        return businessDays.size();
    }


    // udregning af gennemsnitlig antal arbejdstimer pr dag
    public float calculateHoursPrDay(SubTask subTask, int numberOfMembers){
        long result = subTask.getHoursAllocated() / calculateBusinessDays(subTask);
        return (float) result/numberOfMembers;
    }



//     public void addSkillToProject(Project project, String skill){
//        project.addSkillToProject(skill);
//    }

    public void writeProjectToDB(SubTask subTask, int taskId){
        repo.writeSubTaskToDB(subTask, taskId);
    }

    public void deleteSubTask(int taskId){
        repo.deleteSubTask(taskId);
    }

}