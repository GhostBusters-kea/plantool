package com.example.plantool.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Project {

    protected int id;
    protected String name;
    protected Date startDate;
    protected Date endDate;
    protected Date deadline;
    protected ArrayList<ProjectMember> assignees;
    protected int hoursAllocated;
    protected int hoursUsed;
    protected ArrayList<String> skillsAllocated;

    public Project(int id, String name, Date startDate, Date endDate, Date deadline, ArrayList<ProjectMember> assignees, int hoursAllocated, int hoursUsed, ArrayList<String> skillsAllocated) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
        this.assignees = assignees;
        this.hoursAllocated = hoursAllocated;
        this.hoursUsed = hoursUsed;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public ArrayList<ProjectMember> getAssignees() {
        return assignees;
    }

    public void setAssignees(ArrayList<ProjectMember> assignees) {
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
                "projectId=" + id +
                ", projectName='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deadline=" + deadline +
                ", assignees=" + assignees +
                ", hoursAllocated=" + hoursAllocated +
                ", hoursUsed=" + hoursUsed +
                ", skillsAllocated=" + skillsAllocated +
                '}';
    }
}
