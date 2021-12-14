package com.example.plantool.services;

import com.example.plantool.model.Project;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {
    ProjectService service = new ProjectService();
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

    @Test
    void calculateHoursPrDay() {

        // Arrange
        Project project = new Project();
        project.setHoursAllocated(20);
        project.setStartDate(LocalDate.of(2021,12,14));
        project.setEndDate(LocalDate.of(2021,12,21));

        // Act
        //float





    }

    @Test
    void daysUntilDeadline() {
    }
}