package com.example.plantool.model;

import java.time.LocalDate;

/**
 * Author: Jonas Munk
 *
 * SubProject model class that extends project
 */

public class SubProject extends Project{

    // Super Constructor
    public SubProject(String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, int hoursAllocated, String projectDescription) {
        super(name, startDate, endDate, deadline, hoursAllocated, projectDescription);
    }

    // No-Args Constructor
    public SubProject() {
    }

    // Overidden toString method
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
