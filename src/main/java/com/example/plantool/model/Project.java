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
    protected int whoIsLeader;
    protected int hoursAllocated;
    protected int hoursUsed;
    protected ArrayList<String> skillsAllocated;
    protected String projectDescription;


    public Project(String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, int whoIsLeader, int hoursAllocated, String projectDescription) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
        this.whoIsLeader = whoIsLeader;
        this.hoursAllocated = hoursAllocated;
        this.projectDescription = projectDescription;
    }

    public Project() {
    }

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

    public int getWhoIsLeader() {
        return whoIsLeader;
    }

    public void setWhoIsLeader(int whoIsLeader) {
        this.whoIsLeader = whoIsLeader;
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

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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