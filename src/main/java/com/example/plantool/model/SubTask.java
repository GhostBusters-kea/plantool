package com.example.plantool.model;

import java.time.LocalDate;

/**
 * Author: Jonas Munk
 *
 * SubTask model class that extends project
 */

public class SubTask extends Project{

    int taskID;

    // Super Constructor
    public SubTask(String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, int hoursAllocated, String projectDescription) {
        super(name, startDate, endDate, deadline, hoursAllocated, projectDescription);
    }

    // No-Args Constructor
    public SubTask() {
    }

    // Overidden toString method
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

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

}
