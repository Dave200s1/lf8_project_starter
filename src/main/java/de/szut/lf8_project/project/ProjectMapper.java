package de.szut.lf8_project.project;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ProjectMapper {

    public ProjectEntity mapProjectCreateDtoToProject(ProjectCreateDto dto) {
        var project = new ProjectEntity();
        project.setName(dto.getName());
        project.setClientId(dto.getClientId());
        project.setEmployees(dto.getEmployees());
        project.setResponsiblePersonByClientName(dto.getResponsiblePersonByClientName());
        project.setProjectGoal(dto.getProjectGoal());
        project.setStartDate(dto.getStartDate());
        project.setPlannedEndDate(dto.getPlannedEndDate());
        project.setActualEndDate(dto.getActualEndDate());
        return project;
    }

    public ProjectGetDto mapProjectToGetProjectDto(ProjectEntity project){
        ProjectGetDto dto = new ProjectGetDto();
        dto.setPid(project.getPid());
        dto.setName(project.getName());
        dto.setProjectGoal(project.getProjectGoal());
        dto.setClientId(project.getClientId());
        dto.setResponsiblePersonByClientName(project.getResponsiblePersonByClientName());
        dto.setStartDate(project.getStartDate());
        dto.setPlannedEndDate(project.getPlannedEndDate());
        dto.setActualEndDate(project.getActualEndDate());
        return dto;
    }

}
