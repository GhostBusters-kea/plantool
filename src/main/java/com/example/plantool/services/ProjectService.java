package com.example.plantool.services;

import com.example.plantool.model.Project;
import com.example.plantool.repository.ProjectRepo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    ProjectRepo repo = new ProjectRepo();

    public static void main(String[] args) {
        ProjectService service = new ProjectService();

        service.createNewProject("Mobiltelefon", LocalDate.of(2021,12,8),
                LocalDate.of(2021,12,31),LocalDate.of(2021,12,
                        31),200);







    }



    // HENTE ENKELT PROJEKT OG ALLE ALLE PROJEKTER

    // ÆNDRE PROJEKTER

    // SUMMERING AF TIDSFORBRUG PÅ PROJEKTER OG DEL PROJEKTER

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

    }


}
