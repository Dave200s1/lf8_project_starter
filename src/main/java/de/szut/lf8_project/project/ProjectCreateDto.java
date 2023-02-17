package de.szut.lf8_project.project;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.szut.lf8_project.employee.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ProjectCreateDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name at least length of 3 characters")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Client is mandatory")
    private Long clientId;

    @NotBlank(message = "Responsible person by client is mandatory")
    @Size(min = 3, message = "Name at least length of 3 characters")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String responsiblePersonByClientName;

    @NotBlank(message = "Project goal is mandatory")
    @Size(min = 3, message = "Project goal at least length of 3 characters")
    @Size(max = 50, message = "Project goal must not exceed 50 characters")
    private String projectGoal;

    @NotBlank(message = "Start date is mandatory")
    private LocalDate startDate;

    @NotBlank(message = "Planned end date is mandatory")
    private LocalDate plannedEndDate;

    private LocalDate actualEndDate;

    private Set<EmployeeEntity> employees;

    @JsonCreator
    public ProjectCreateDto(String name) {
        this.name = name;
    }
}