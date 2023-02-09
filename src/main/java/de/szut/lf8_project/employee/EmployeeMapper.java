package de.szut.lf8_project.employee;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EmployeeMapper {
    public EmployeeEntity mapEmployeeCreateDtoToEmployee(EmployeeCreateDto dto){
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(dto.getName());
        return employee;
    }

    public EmployeeGetDto mapEmployeeToGetEmployeeDto(EmployeeEntity employee){
        EmployeeGetDto dto = new EmployeeGetDto();
        dto.setEid(employee.getEid());
        return dto;
    }

}
