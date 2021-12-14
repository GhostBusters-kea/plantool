package com.example.plantool.services;

import com.example.plantool.model.Member;
import com.example.plantool.model.Project;
import com.example.plantool.repository.ProjectRepo;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProjectService {
    ProjectRepo repo = new ProjectRepo();


    public void updateProjectName(int projectid, String name) {
        repo.updateProjectName(projectid, name);
    }

    public void updateProjectStartDate(int projectid, LocalDate startdate) {
        repo.updateProjectStartDate(projectid, startdate);
    }

    public void updateProjectsEndDate(int projectid, LocalDate enddate) {
        repo.updateProjectEndDate(projectid, enddate);
    }

    public void updateDeadline(int projectid, LocalDate deadline) {
        repo.updateProjectDeadline(projectid, deadline);
    }

    public void updateHoursAllocated(int projectid, int hours) {
        repo.updateHoursAllocated(projectid, hours);

    }

    public void updateHoursUsed(int projectid, int hours) {
        repo.updateHoursUsed(projectid, hours);
    }

    public void updateProjectDescription(int projectid, String description) {
        repo.updateDescription(projectid, description);
    }

    // opret nyt projekt
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

    public void addProjectToDb(Project project) {
        repo.writeProjectToDB(project);
    }

    // hent enkelt projekt
    public Project fetchSingleProject(int projectID) {
        return repo.fetchSingleProject(projectID);
    }

    public int fetchSingelProjectId(String projectName) {
        return repo.fetchSingleProjectId(projectName);
    }

    // hent alle projekter
    public ArrayList<Project> fetchAllProjects() {
        return repo.fetchAllProjects();
    }


    // knytter projektdeltager til bestemt project
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


    // checker om projektdeltager allerede er knyttet til projekt
    public boolean projectHasMember(int projectId, int memberId) {

        for (int i = 0; i < repo.membersInProject(projectId).size(); i++) {
            if (repo.membersInProject(projectId).get(i).equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    // metode til udregning af arbejdsdage
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

    // metode nummer 2  - kan ændres til at returnere en liste med alle dagene mellem start og end
    public int countBusinessDays(Project project) {

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;

        List<LocalDate> businessDays = project.getStartDate().datesUntil(project.getEndDate())
                .filter(isWeekend.negate())
                .collect(Collectors.toList());
        if (businessDays.size() == 0) {
            return 1;
        } else {
            return businessDays.size();
        }
    }


    // udregning af gennemsnitlig antal arbejdstimer pr dag
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