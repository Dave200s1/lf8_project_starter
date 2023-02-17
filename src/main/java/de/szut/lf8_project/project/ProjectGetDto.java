package de.szut.lf8_project.project;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectGetDto {
    private Long pid;
    private String name;
    private Long clientId;
    private String responsiblePersonByClientName;
    private String projectGoal;
    private LocalDate startDate;
    private LocalDate plannedEndDate;
    private LocalDate actualEndDate;

}

