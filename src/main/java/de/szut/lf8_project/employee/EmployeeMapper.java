package de.szut.lf8_project.employee;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EmployeeMapper {
    public EmployeeEntity mapEmployeeCreateDtoToEmployee(EmployeeCreateDto dto){
        EmployeeEntity employee = new EmployeeEntity();
        employee.setLastName(dto.getLastName());
        employee.setFirstName(dto.getFirstName());
        employee.setStreet(dto.getStreet());
        employee.setPostcode(dto.getPostcode());
        employee.setCity(dto.getCity());
        employee.setPhone(dto.getPhone());
        employee.setProject(dto.getProject());
        return employee;
    }

    public EmployeeGetDto mapEmployeeToGetEmployeeDto(EmployeeEntity employee){
        EmployeeGetDto dto = new EmployeeGetDto();
        dto.setEid(employee.getEid());
        dto.setLastName(employee.getLastName());
        dto.setFirstName(employee.getFirstName());
        dto.setStreet(employee.getStreet());
        dto.setPostcode(employee.getPostcode());
        dto.setCity(employee.getCity());
        dto.setPhone(employee.getPhone());
        return dto;
    }

}
