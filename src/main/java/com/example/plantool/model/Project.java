package com.example.plantool.model;
import java.security.ProtectionDomain;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;


public class Project {

    protected int id;
    protected String name;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalDate deadline;
    protected ArrayList<Member> assignees;
    protected String whoIsLeader;
    protected int hoursAllocated;
    protected int hoursUsed;
    protected ArrayList<String> skillsAllocated;
    protected String projectDescription;

    public String getWhoIsLeader() {
        return whoIsLeader;
    }

    public void setWhoIsLeader(String whoIsLeader) {
        this.whoIsLeader = whoIsLeader;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Project(int id, String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, ArrayList<Member> assignees, int hoursAllocated, int hoursUsed, ArrayList<String> skillsAllocated) {

        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
        this.assignees = assignees;
        this.hoursAllocated = hoursAllocated;
        this.skillsAllocated = skillsAllocated;
    }

    public Project(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public ArrayList<Member> getAssignees() {
        return assignees;
    }

    public void setAssignees(ArrayList<Member> assignees) {
        this.assignees = assignees;
    }

    public int getHoursAllocated() {
        return hoursAllocated;
    }

    public void setHoursAllocated(int hoursAllocated) {
        this.hoursAllocated = hoursAllocated;
    }

    public int getHoursUsed() {
        return hoursUsed;
    }

    public void setHoursUsed(int hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

    public ArrayList<String> getSkillsAllocated() {
        return skillsAllocated;
    }

    public void setSkillsAllocated(ArrayList<String> skillsAllocated) {
        this.skillsAllocated = skillsAllocated;
    }

    // hvorfor virker dette ikke?
    public void addSkillToProject(String skill){
        skillsAllocated.add(skill);
    }

    public void addMemberToProject(Member member){
        assignees.add(member);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return id == project.id && hoursAllocated == project.hoursAllocated && hoursUsed == project.hoursUsed && name.equals(project.name) && startDate.equals(project.startDate) && endDate.equals(project.endDate) && deadline.equals(project.deadline) && assignees.equals(project.assignees) && skillsAllocated.equals(project.skillsAllocated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, deadline, assignees, hoursAllocated, hoursUsed, skillsAllocated);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deadline=" + deadline +
                ", assignees=" + assignees +
                ", whoIsLeader='" + whoIsLeader + '\'' +
                ", hoursAllocated=" + hoursAllocated +
                ", hoursUsed=" + hoursUsed +
                ", skillsAllocated=" + skillsAllocated +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }
}
