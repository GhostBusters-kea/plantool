package com.example.plantool.services;

import com.example.plantool.model.Project;
import com.example.plantool.repository.ProjectRepo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    ProjectRepo repo = new ProjectRepo();


    public void createProject(String projectName, LocalDate startDate, LocalDate endDate, LocalDate deadline, int hoursAllocated, int hoursUsed{
        Project project = new Project();
        project.setName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setDeadline(deadline);
        project.setHoursAllocated(hoursAllocated);
        project.setHoursUsed(hoursUsed);
        repo.writeProjectToDB(project);
    }


    





    public void deleteProject(int projectID){

    }



}
