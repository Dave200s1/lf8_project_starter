package de.szut.lf8_project.project;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public ProjectEntity create(ProjectEntity newProject) {
        return repository.save(newProject);
    }

    public List<ProjectEntity> readAll() {
        return this.repository.findAll();
    }

    public ProjectEntity readById(Long id) {
        Optional<ProjectEntity> optionalQualification = this.repository.findById(id);
        if (optionalQualification.isEmpty()) {
            throw new ResourceNotFoundException("Project not found on id = " + id);
        }
        return optionalQualification.get();
    }

    public ProjectEntity update(ProjectEntity project) {
        ProjectEntity updatedProjekt = readById(project.getPid());
        Optional<ProjectEntity> optionalQualification = this.repository.findById(project.getPid());
        if (optionalQualification.isEmpty()) {
            throw new ResourceNotFoundException("Project not found on id = " + project.getPid());
        }
        updatedProjekt.setName(project.getName());
        updatedProjekt.setClientId(project.getClientId());
        updatedProjekt.setEmployees(project.getEmployees());
        updatedProjekt.setResponsiblePersonByClientName(project.getResponsiblePersonByClientName());
        updatedProjekt.setProjectGoal(project.getProjectGoal());
        updatedProjekt.setStartDate(project.getStartDate());
        updatedProjekt.setPlannedEndDate(project.getPlannedEndDate());
        updatedProjekt.setActualEndDate(project.getActualEndDate());
        updatedProjekt = this.repository.save(updatedProjekt);
        return updatedProjekt;
    }

    public void delete(ProjectEntity project) {
        Optional<ProjectEntity> optionalQualification = this.repository.findById(project.getPid());
        if (optionalQualification.isEmpty()) {
            throw new ResourceNotFoundException("Project not found on id = " + project.getPid());
        }
        this.repository.delete(project);
    }


}
