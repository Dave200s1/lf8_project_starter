package de.szut.lf8_project.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EmployeeCreateDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name at least length of 3 characters")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @JsonCreator
    public EmployeeCreateDto(String name) {
        this.name = name;
    }
}