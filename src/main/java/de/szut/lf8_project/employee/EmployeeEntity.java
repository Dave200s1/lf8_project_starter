package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;

    private String lastName;
    private String firstName;
    private String street;
    private String postcode;
    private String city;
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "eid"),
            inverseJoinColumns = @JoinColumn(name = "pid"))
    private Set<ProjectEntity> project;
}
