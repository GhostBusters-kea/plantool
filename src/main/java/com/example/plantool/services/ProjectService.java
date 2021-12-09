package com.example.plantool.services;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.repository.ProjectRepo;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    ProjectRepo repo = new ProjectRepo();

    public static void main(String[] args) {

        ProjectService ps = new ProjectService();

        System.out.println(ps.fetchSingleProject(19));


    }

    public void createSkill(String skill, int projectid){
        repo.writeSkillToDB(skill, projectid);
    }


    // opret nyt projekt
    public Project createNewProject(String projectName, LocalDate startDate, LocalDate endDate, LocalDate deadline,
                                 int hoursAllocated, int whoIsLeader, String description){
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

    public void addProjectToDb(Project project){
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
    // TODO: Matematikkyndig person skal kigge på denne metode
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

    // tilknyt deltager til projekt
//    public void addMemberToProject(Member member, Project project){
//        project.addMemberToProject(member);
//
//    }


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

//     public void addSkillToProject(Project project, String skill){
//        project.addSkillToProject(skill);
//    }

    public void writeProjectToDB(Project project){
        repo.writeProjectToDB(project);
    }

    public void deleteProject(int projectID){
        repo.deleteProject(projectID);
    }

}
