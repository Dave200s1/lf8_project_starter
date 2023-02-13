package de.szut.lf8_project.project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "project")
public class ProjectController {
    private final ProjectService service;
    private final ProjectMapper mappingService;

    public ProjectController(ProjectService service, ProjectMapper mappingService) {
        this.service = service;
        this.mappingService = mappingService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProjectGetDto> createProject(@Valid @RequestBody final ProjectCreateDto dto){
        ProjectEntity newProject = this.mappingService.mapProjectCreateDtoToProject(dto);
        newProject =  this.service.create(newProject);
        final ProjectGetDto request = this.mappingService.mapProjectToGetProjectDto(newProject);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectGetDto> getProjektById(@PathVariable final Long id){
        final var entity = this.service.readById(id);
        final ProjectGetDto dto = this.mappingService.mapProjectToGetProjectDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProjectGetDto> updateProject(@PathVariable final Long id, @Valid @RequestBody final ProjectCreateDto dto) {
        ProjectEntity updatedProject = this.mappingService.mapProjectCreateDtoToProject(dto);
        updatedProject.setPid(id);
        updatedProject = this.service.update(updatedProject);
        ProjectGetDto request = this.mappingService.mapProjectToGetProjectDto(updatedProject);
        return new ResponseEntity<>(request,HttpStatus.OK);
    }


}
