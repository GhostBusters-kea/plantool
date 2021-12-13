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
    SubTaskRepo subTaskrepo = new SubTaskRepo();


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
        subTaskrepo.writeSubTaskToDB(subTask, taskid);
    }

    // hent enkelt projekt
    public SubTask fetchSingleSubTask(int subTaskID){
        return subTaskrepo.fetchSingleSubTask(subTaskID);
    }

    // hent alle projekter
    public ArrayList<SubTask> fetchAllSubTask(int taskId){
        return subTaskrepo.fetchAllSubTask(taskId);
    }

    public void assignMemberToSubTask(int subTaskId, int memberId){
        if (subTaskHasMember(subTaskId, memberId)){
            System.out.println("Member already assigned to project");
        }

        else {
            subTaskrepo.assignMemberToSubTask(subTaskId, memberId);
        }
    }

    public ArrayList<Integer> membersInSubTask(int taskId){
        return subTaskrepo.membersInSubTask(taskId);
    }



    public boolean subTaskHasMember(int subTaskId, int memberId){

        for(int i = 0; i < subTaskrepo.membersInSubTask(subTaskId).size(); i++){
            if(subTaskrepo.membersInSubTask(subTaskId).get(i).equals(memberId)){
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



    // metoder til af ændre projekter
    public void addSubTaskName(SubTask subTask, String name){
        subTask.setName(name);
    }

    public void addSubTaskDates(SubTask subTask, LocalDate startDate, LocalDate endDate){
        subTask.setStartDate(startDate);
        subTask.setEndDate(endDate);
    }

    public void addSubTaskDeadline(SubTask subTask, LocalDate deadline){
        subTask.setDeadline(deadline);
    }

    public void addSubTaskHours(SubTask subTask, int hoursAllocated, int hoursUsed){
        subTask.setHoursAllocated(hoursAllocated);
        subTask.setHoursUsed(hoursUsed);
    }

//     public void addSkillToProject(Project project, String skill){
//        project.addSkillToProject(skill);
//    }

    public void writeProjectToDB(SubTask subTask, int taskId){
        subTaskrepo.writeSubTaskToDB(subTask, taskId);
    }

    public void deleteSubTask(int taskId){
        subTaskrepo.deleteSubTask(taskId);
    }

}