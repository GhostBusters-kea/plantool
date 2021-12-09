package com.example.plantool.services;

import com.example.plantool.model.Project;
import com.example.plantool.repository.ProjectRepo;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    ProjectRepo repo = new ProjectRepo();

    // opret nyt projekt
    public void createNewProject(String projectName, LocalDate startDate,
                                 LocalDate endDate, LocalDate deadline, int hoursAllocated){
        Project project = new Project();
        project.setName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setDeadline(deadline);
        project.setHoursAllocated(hoursAllocated);
        repo.writeProjectToDB(project);
    }

    // hent enkelt projekt
    public Project fetchSingleProject(int projectID){
        return repo.fetchSingleProject(projectID);
    }

    // hent alle projekter
    public ArrayList<Project> fetchAllProjects(){
        return repo.fetchAllProjects();
    }

    // metode til udregning af arbejdsdage
    // TODO: Matematikkundig person skal kigge på denne metode
    public static long calculateBusinessDays(Project project){
        int first = project.getStartDate().getDayOfWeek().getValue();
        int last = project.getEndDate().getDayOfWeek().getValue();

        long totalDays =
                ChronoUnit.DAYS.between(project.getStartDate(),project.getEndDate());

        long result = totalDays - 2*(totalDays/7);

        if(totalDays %7 !=0){
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


    // udregning af gennemsnitlig antal arbejdstimer pr dag
    public long calculateHoursPrDay(Project project){
        long result = project.getHoursAllocated() / calculateBusinessDays(project);
        return result;
    }


    // metoder til af ændre projekter
    public void addProjectName(Project project, String name){
        project.setName(name);
    }

    public void addProjectDates(Project project, LocalDate startDate, LocalDate endDate){
        project.setStartDate(startDate);
        project.setEndDate(endDate);
    }

    public void addProjectDeadline(Project project, LocalDate deadline){
        project.setDeadline(deadline);
    }

    public void addProjectHours(Project project, int hoursAllocated, int hoursUsed){
        project.setHoursAllocated(hoursAllocated);
        project.setHoursUsed(hoursUsed);
    }

    public void addSkillToProject(Project project, String skill){
        project.addSkillToProject(skill);
    }

    public void writeProjectToDB(Project project){
        repo.writeProjectToDB(project);
    }

    public void deleteProject(int projectID){
        repo.deleteProject(projectID);
    }

}
