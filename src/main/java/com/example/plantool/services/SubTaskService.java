package com.example.plantool.services;

import com.example.plantool.model.SubTask;
import com.example.plantool.repository.SubTaskRepo;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Author: Kevin
 *
 * Subtask service for funktionality
 */
public class SubTaskService {
    SubTaskRepo repo = new SubTaskRepo();

    public void updateHoursAllocated(int subtaskid, int hours){
        repo.updateHoursAllocated(subtaskid,hours);
    }


    // Creates new project
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

    //Inserts subtask to database
    public void addSubTaskToDB(SubTask subTask, int taskid){
        repo.writeSubTaskToDB(subTask, taskid);
    }

    // fetch all subtasks
    public ArrayList<SubTask> fetchAllSubTask(int taskId){
        return repo.fetchAllSubTask(taskId);
    }

    //Assign member to subtask
    public void assignMemberToSubTask(int subTaskId, int memberId){
        if (subTaskHasMember(subTaskId, memberId)){
            System.out.println("Member already assigned to project");
        }

        else {
            repo.assignMemberToSubTask(subTaskId, memberId);
        }
    }

    // Check if subtask has member
    public boolean subTaskHasMember(int subTaskId, int memberId){

        for(int i = 0; i < repo.membersInSubTask(subTaskId).size(); i++){
            if(repo.membersInSubTask(subTaskId).get(i).equals(memberId)){
                return true;
            }
        }
        return false;
    }

    // Method to calculate business days
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

    // method number 2 - can be changed to return a list of all the days between start and end
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