package com.example.plantool.services;

import com.example.plantool.model.Project;
import com.example.plantool.model.SubProject;
import com.example.plantool.repository.SubprojectsRepo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Author: Lars Brogaard
 *
 * Subproject service for funktionality
 */

public class SubProjectService {
    SubprojectsRepo repo = new SubprojectsRepo();

    // Update subproject name
    public void updatesubProjectName(int subprojectid, String name){
        repo.updateSubProjectName(subprojectid, name);
    }

    // Update subproject start date
    public void updateProjectStartDate(int subprojectid, LocalDate startdate){
        repo.updateSubProjectStartDate(subprojectid,startdate);
    }

    // Update subproject deadline
    public void updateDeadline(int subprojectid, LocalDate deadline){
        repo.updateSubProjectDeadline(subprojectid,deadline);
    }

    // Update subproject hours allocated
    public void updateHoursAllocated(int subprojectid, int hours){
        repo.updateHoursAllocated(subprojectid,hours);

    }

    // Update subproject description
    public void updatesubProjectDescription(int subprojectid, String description){
        repo.updateDescription(subprojectid,description);
    }


    // Creates new subproject
    public Project createNewSubProject(int projectid, String projectName, LocalDate startDate, LocalDate endDate, LocalDate deadline,
                                    int hoursAllocated, String description){
        Project project = new Project();
        project.setName(projectName);
        project.setStartDate(startDate);
        project.setStartDate(endDate);
        project.setDeadline(deadline);
        project.setHoursAllocated(hoursAllocated);
        project.setProjectDescription(description);

        repo.writeSubProjectToDB(project,projectid);

        return project;

    }

    // Add subproject database
    public void addSubProjectToDb(SubProject subProject, int projectid){
        repo.writeSubProjectToDB(subProject,projectid);
    }

    // fetch single subproject
    public Project fetchSingleSubProject(int subprojectID){
        return repo.fetchSingleSubProject(subprojectID);
    }


    // fetch all subproject from project
    public ArrayList<SubProject> fetchAllSubProjectsFromProject(int projectid) {
        return repo.fetchSubProjectsFromProject(projectid);
    }


        // assign member to subproject
        public void assignMemberToSubProject (int subprojectId, int memberId){

            if (subProjectHasMember(subprojectId,memberId)) {
                System.out.println("Member already assigned to project");
            } else {
                repo.assignMemberToSubProject(subprojectId, memberId);
            }
        }


        public ArrayList<Integer> membersInSubProject (int subprojectId){
            return repo.membersInSubProject(subprojectId);
        }


        // checks if the project participant is already linked to the project
        public boolean subProjectHasMember (int subprojectId, int memberId){
            for (int i = 0; i < repo.membersInSubProject(subprojectId).size(); i++) {
                if (repo.membersInSubProject(subprojectId).get(i)==memberId) {
                    return true;
                }
            }
            return false;
        }

        // method of calculating working days
        public static long calculateBusinessDays (Project project){
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
        public static int countBusinessDays (Project project){

            Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    date.getDayOfWeek() == DayOfWeek.SUNDAY;

            List<LocalDate> businessDays = project.getStartDate().datesUntil(project.getEndDate())
                    .filter(isWeekend.negate())
                    .collect(Collectors.toList());

            return businessDays.size();
        }


        // calculation of average number of working hours per day
        public float calculateHoursPrDay (Project project,int numberOfMembers){
            long result = project.getHoursAllocated() / calculateBusinessDays(project);
            return (float) result / numberOfMembers;
        }


        // metoder til af ændre projekter
        public void addProjectName (Project project, String name){
            project.setName(name);
        }

        public void addProjectDates (Project project, LocalDate startDate, LocalDate endDate){
            project.setStartDate(startDate);
            project.setEndDate(endDate);
        }

        public void addProjectDeadline (Project project, LocalDate deadline){
            project.setDeadline(deadline);
        }

        public void addProjectHours (Project project,int hoursAllocated, int hoursUsed){
            project.setHoursAllocated(hoursAllocated);
            project.setHoursUsed(hoursUsed);
        }


        public void writeProjectToDB (Project project, int projectid){
            repo.writeSubProjectToDB(project,projectid);
        }

        //Deletes subproject
        public void deletesubProject ( int subprojectid){
            repo.deleteSubProject(subprojectid);
        }


    }

