package com.example.plantool.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Task extends Project{

    Task(int id, String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, ArrayList<ProjectMember> assignees, int hoursAllocated, ArrayList<String> skillsAllocated) {
        super(id, name, startDate, endDate, deadline, assignees, hoursAllocated, skillsAllocated);

    }
}
