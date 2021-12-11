package com.example.plantool.model;

import java.time.LocalDate;

public class SubTask extends Project{
    public SubTask(String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, int hoursAllocated, String projectDescription) {
        super(name, startDate, endDate, deadline, hoursAllocated, projectDescription);
    }

    public SubTask() {
    }

    @Override
    public String toString() {
        return "SubTask{" +
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
