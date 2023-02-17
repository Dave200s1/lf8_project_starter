package de.szut.lf8_project.employee;

import lombok.Data;

@Data
public class EmployeeGetDto {
    private Long eid;
    private String lastName;
    private String firstName;
    private String street;
    private String postcode;
    private String city;
    private String phone;
}