package com.example.plantool.services;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.repository.ProjectRepo;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Author: Lars Brogaard
 *
 * Project service for funktionality
 */

public class ProjectService {
    ProjectRepo repo = new ProjectRepo();

    //Update project name
    public void updateProjectName(int projectid, String name) {
        repo.updateProjectName(projectid, name);
    }

    //Update project start date
    public void updateProjectStartDate(int projectid, LocalDate startdate) {
        repo.updateProjectStartDate(projectid, startdate);
    }

    //Update project end date
    public void updateProjectsEndDate(int projectid, LocalDate enddate) {
        repo.updateProjectEndDate(projectid, enddate);
    }

    //Update deadline
    public void updateDeadline(int projectid, LocalDate deadline) {
        repo.updateProjectDeadline(projectid, deadline);
    }

    //Update hours allocated
    public void updateHoursAllocated(int projectid, int hours) {
        repo.updateHoursAllocated(projectid, hours);

    }

    public void updateHoursUsed(int projectid, int hours) {
        repo.updateHoursUsed(projectid, hours);
    }

    //update project description
    public void updateProjectDescription(int projectid, String description) {
        repo.updateDescription(projectid, description);
    }

    // Create a new project
    public Project createNewProject(String projectName, LocalDate startDate, LocalDate endDate, LocalDate deadline,
                                    int hoursAllocated, int whoIsLeader, String description) {
        Project project = new Project();
        project.setName(projectName);
        project.setStartDate(startDate);
        project.setStartDate(endDate);
        project.setDeadline(deadline);
        project.setHoursAllocated(hoursAllocated);
        project.setWhoIsLeader(whoIsLeader);
        project.setProjectDescription(description);

        return project;

    }

    //Deletes project
    public void deleteProject(int projectId){
        repo.deleteProject(projectId);
    }

    //Add a project to database
    public void addProjectToDb(Project project) {
        repo.writeProjectToDB(project);
    }

    // Fetch single project
    public Project fetchSingleProject(int projectID) {
        return repo.fetchSingleProject(projectID);
    }

    //Fetch single project on id
    public int fetchSingelProjectId(String projectName) {
        return repo.fetchSingleProjectId(projectName);
    }

    // fetch all projects
    public ArrayList<Project> fetchAllProjects() {
        return repo.fetchAllProjects();
    }


    // Assign member to project
    public void assignMemberToProject(int projectId, int memberId) {

        if (projectHasMember(projectId, memberId)) {
            System.out.println("Member already assigned to project");
        } else {
            repo.assignMemberToProject(projectId, memberId);
        }
    }

    public ArrayList<Integer> membersInProject(int projectId) {
        return repo.membersInProject(projectId);
    }


    // checks if project already have an instance of a member
    public boolean projectHasMember(int projectId, int memberId) {

        for (int i = 0; i < repo.membersInProject(projectId).size(); i++) {
            if (repo.membersInProject(projectId).get(i).equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    // Method to calculate business days
    public static long calculateBusinessDays(Project project) {
        int first = project.getStartDate().getDayOfWeek().getValue();
        int last = project.getEndDate().getDayOfWeek().getValue();

        long totalDays =
                ChronoUnit.DAYS.between(project.getStartDate(), project.getEndDate());

        long result = totalDays - 2 * (totalDays / 7);

        if (totalDays % 7 != 0) {
            if (first == 7) {
                result -= 1;
            } else if (last == 7) {
                result -= 1;
            } else if (last < first) {
                result -= 2;
            }
        }
        return result;
    }

    // method number 2 - can be changed to return a list of all the days between start and end
    public int countBusinessDays(Project project) {

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;
        try{
            List<LocalDate> businessDays = project.getStartDate().datesUntil(project.getEndDate())
                    .filter(isWeekend.negate())
                    .collect(Collectors.toList());
            if (businessDays.size() == 0) {
                return 1;
            } else {
                return businessDays.size();
            }
        }
        catch(IllegalArgumentException ex){
            return 0;
        }
    }


    // calculation of average number of working hours per day
    public float calculateHoursPrDay(Project project, int numberOfMembers) {
        float days = 0;
        if (calculateBusinessDays(project) == 0) {
            days = 1;
        } else {
            days = calculateBusinessDays(project);
        }
        float result = project.getHoursAllocated() / days;

        if (numberOfMembers == 0) {
            numberOfMembers = 1;
        }
        return (float) result / numberOfMembers;
    }

    //Calculate days until deadline
    public int daysUntilDeadline(Project project) {
        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;

        if (LocalDate.now().isBefore(project.getEndDate())) {
            List<LocalDate> daysLeft = LocalDate.now().datesUntil(project.getEndDate())
                    .filter(isWeekend.negate())
                    .collect(Collectors.toList());
            return daysLeft.size();
        } else {
            return 0;
        }
    }

}