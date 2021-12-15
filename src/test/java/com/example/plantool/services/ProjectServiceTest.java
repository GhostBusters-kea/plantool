package com.example.plantool.services;

import com.example.plantool.model.Project;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Author: Lars Brogaard
 *
 * Test 2 methods from projectservice
 */

class ProjectServiceTest {
    ProjectService service = new ProjectService();

    //Test business days count / Successful test
    @Test
    void countBusinessDays() {

        // Arrange
        Project project = new Project();
        project.setStartDate(LocalDate.of(2021,12,14));
        project.setEndDate(LocalDate.of(2021,12,21));

        // Act
        int days = service.countBusinessDays(project);

        // Assert
        assertEquals(5,days);
    }

    //Test on calculate hours per day / Successful test
    @Test
    void calculateHoursPrDay() {

        // Arrange
        Project project = new Project();
        project.setHoursAllocated(20);
        project.setStartDate(LocalDate.of(2021,12,14));
        project.setEndDate(LocalDate.of(2021,12,21));

        // Act
        float hours = service.calculateHoursPrDay(project,1);

        // Assert
        assertEquals(4,hours);

    }

}