package com.example.plantool.model;

import java.util.ArrayList;
import java.util.Date;

public class SubTask extends Project{
    SubTask(int id, String name, Date startDate, Date endDate, Date deadline, ArrayList<ProjectMember> assignees, int hoursAllocated, int hoursUsed, ArrayList<skill> skillsAllocated) {
        super(id, name, startDate, endDate, deadline, assignees, hoursAllocated, hoursUsed, skillsAllocated);
    }
}
