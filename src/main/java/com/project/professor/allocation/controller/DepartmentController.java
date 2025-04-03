package com.project.professor.allocation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Tag(name = "Departments")
@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentRepository repository;

    public DepartmentController(DepartmentRepository repository) {
        super();
        this.repository = repository;
    }

    @Operation(summary = "Find a department")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
        Department department = repository.findById(id).orElse(null);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
    }

    @Operation(summary = "Save a department")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> save(@RequestBody Department department) {
        try {
            department = repository.save(department);
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Operation(summary = "Update a department")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id,
                                             @RequestBody Department department) {
        department.setId(id);
        try {
            department = repository.save(department);
            if (department == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(department, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Operation(summary = "Delete a department")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping(path = "/{department_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete all departments")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}