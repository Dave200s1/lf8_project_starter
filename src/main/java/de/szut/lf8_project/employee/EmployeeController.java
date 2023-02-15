package de.szut.lf8_project.employee;

import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "employee")
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService service, EmployeeMapper employeeMapper) {
        this.service = service;
        this.employeeMapper = employeeMapper;
    }

    @Operation(summary = "creates a new employee with its id and message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping
    public EmployeeGetDto create(@RequestBody @Valid EmployeeCreateDto employeeCreateDto) {
        EmployeeEntity employeeEntity = this.employeeMapper.mapEmployeeCreateDtoToEmployee(employeeCreateDto);
        employeeEntity = this.service.create(employeeEntity);
        return this.employeeMapper.mapEmployeeToGetEmployeeDto(employeeEntity);
    }

    @Operation(summary = "delivers a list of employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of employees",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping
    public List<EmployeeGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(e -> this.employeeMapper.mapEmployeeToGetEmployeeDto(e))
                .collect(Collectors.toList());
    }

    @Operation(summary = "deletes an employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@RequestParam long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("EmployeeEntity not found on id = " + id);
        } else {
            this.service.delete(entity);
        }
    }

    @Operation(summary = "find employees by message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees who have the given message",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "qualification description does not exist",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/findByMessage")
    public List<EmployeeGetDto> findAllEmployeesByQualification(@RequestParam String message) {
        return this.service
                .findByMessage(message)
                .stream()
                .map(e -> this.employeeMapper.mapEmployeeToGetEmployeeDto(e))
                .collect(Collectors.toList());
    }
}
