package com.example.plantool.model;

import java.time.LocalDate;

public class Task extends Project{
    int subprojectId;

    public Task(int subprojectId, String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, int hoursAllocated, String projectDescription) {
        super(name, startDate, endDate, deadline, hoursAllocated, projectDescription);
        this.subprojectId = subprojectId;
    }

    public Task() {
    }


    public int getSubprojectId() {
        return subprojectId;
    }

    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deadline=" + deadline +
                ", hoursAllocated=" + hoursAllocated +
                ", hoursUsed=" + hoursUsed +
                ", projectDescription='" + projectDescription + '\'' +
                ", subprojectId=" + subprojectId +
                '}';
    }
}
