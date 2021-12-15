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
    public void updateSubDeadline(int subprojectid, LocalDate deadline){
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


    // Add subproject database
    public void addSubProjectToDb(SubProject subProject, int projectid){
        repo.writeSubProjectToDB(subProject,projectid);
    }


    // fetch all subproject from project
    public ArrayList<SubProject> fetchAllSubProjectsFromProject(int projectid) {
        return repo.fetchSubProjectsFromProject(projectid);
    }


        // assign member to subproject - not implementet
        public void assignMemberToSubProject (int subprojectId, int memberId){

            if (subProjectHasMember(subprojectId,memberId)) {
                System.out.println("Member already assigned to project");
            } else {
                repo.assignMemberToSubProject(subprojectId, memberId);
            }
        }


        // not implementet
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

        //Deletes subproject
        public void deletesubProject ( int subprojectid){
            repo.deleteSubProject(subprojectid);
        }


    }

