package com.example.plantool.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SubProject extends Project {

  project_controller_&_templates
    SubProject(int id, String name, LocalDate startDate, LocalDate endDate, LocalDate deadline, ArrayList<Member> assignees, int hoursAllocated, int hoursUsed, ArrayList<String> skillsAllocated) {
        super(id, name, startDate, endDate, deadline, assignees, hoursAllocated, hoursUsed, skillsAllocated);


 
    }
}
