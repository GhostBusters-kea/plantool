package com.example.plantool.model;

import java.time.LocalDate;
/**
 * Author: Jonas Munk
 *
 * SubTask model class that extends project
 */

public class Task extends Project{
    int subprojectId;

    // Super Constructor
    public Task(String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, int hoursAllocated, String projectDescription) {
        super(name, startDate, endDate, deadline, hoursAllocated, projectDescription);
    }

    // No-Args Constructor
    public Task() {
    }

    // Getters and Setters
    public int getSubprojectId() {
        return subprojectId;
    }

    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }

    // Overridden toString method
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
