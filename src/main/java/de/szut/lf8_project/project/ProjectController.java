package de.szut.lf8_project.project;

import de.szut.lf8_project.employee.EmployeeGetDto;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    private final ProjectService service;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService service, ProjectMapper projectMapper) {
        this.service = service;
        this.projectMapper = projectMapper;
    }

    @Operation(summary = "creates a new project with its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "project created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<ProjectGetDto> createProject(@Valid @RequestBody final ProjectCreateDto dto){
        ProjectEntity newProject = this.projectMapper.mapProjectCreateDtoToProject(dto);
        newProject =  this.service.create(newProject);
        final ProjectGetDto request = this.projectMapper.mapProjectToGetProjectDto(newProject);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @Operation(summary = "delivers a list of projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of projects",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping
    public List<ProjectGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(e -> this.projectMapper.mapProjectToGetProjectDto(e))
                .collect(Collectors.toList());
    }

    @Operation(summary = "delivers project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "project requested successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<ProjectGetDto> getProjektById(@PathVariable final Long id){
        final var entity = this.service.readById(id);
        final ProjectGetDto dto = this.projectMapper.mapProjectToGetProjectDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "update a project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<ProjectGetDto> updateProject(@PathVariable final Long id, @Valid @RequestBody final ProjectCreateDto dto) {
        ProjectEntity updatedProject = this.projectMapper.mapProjectCreateDtoToProject(dto);
        updatedProject.setPid(id);
        updatedProject = this.service.update(updatedProject);
        ProjectGetDto request = this.projectMapper.mapProjectToGetProjectDto(updatedProject);
        return new ResponseEntity<>(request,HttpStatus.OK);
    }

    @Operation(summary = "deletes a project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public void deleteProjectById(@RequestParam Long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Project not found on id = " + id);
        } else {
            this.service.delete(entity);
        }
    }
}
