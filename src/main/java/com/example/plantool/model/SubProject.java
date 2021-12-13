package com.example.plantool.model;

import java.time.LocalDate;

public class SubProject extends Project{
    public SubProject(String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, int hoursAllocated, String projectDescription) {
        super(name, startDate, endDate, deadline, hoursAllocated, projectDescription);
    }

    public SubProject() {
    }


    @Override
    public String toString() {
        return "SubProject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deadline=" + deadline +
                ", assignees=" + assignees +
                ", whoIsLeader=" + whoIsLeader +
                ", hoursAllocated=" + hoursAllocated +
                ", hoursUsed=" + hoursUsed +
                ", skillsAllocated=" + skillsAllocated +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }
}
