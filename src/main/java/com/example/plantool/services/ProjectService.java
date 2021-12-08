package com.example.plantool.services;

import com.example.plantool.model.Project;
import com.example.plantool.repository.ProjectRepo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    ProjectRepo repo = new ProjectRepo();
/*
    public static void main(String[] args) {
        ProjectService ps = new ProjectService();
        Project p = new Project();

        ps.addProjectHours(p,100,20);
        ps.addProjectName(p,"Test300");

        System.out.println(p);
        System.out.println(p.getSkillsAllocated());
        ps.writeProjectToDB(p);

    }

*/

    // TODO: opret projekt med pARAM,ET

    // HENTE ENKELT PROJEKT OG ALLE ALLE PROJEKTER

    // ÆNDRE PROJEKTER

    // SUMMERING AF TIDSFORBRUG PÅ PROJEKTER OG DEL PROJEKTER


    public Project createNewProject(){
        Project project = new Project();
        return project;
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
