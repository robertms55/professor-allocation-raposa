package com.project.professor.allocation.controller;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.*;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
private final ProfessorRepository repository;
private final ProfessorService service;
public ProfessorController (ProfessorRepository repository,ProfessorService service){
    super();
    this.repository = repository;
    this.service = service;
}

    @Operation(summary = "Find a professor ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })

    @GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {
        Professor professor = service.findById(id);
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        }
    }

    @Operation(summary = "Save a professor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> save(@RequestBody Professor professor) {
        try {
            professor = service.save(professor);
            return new ResponseEntity<>(professor, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


    @Operation(summary = "Update a professor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> update(@PathVariable(name = "professor_id") Long id,
                                             @RequestBody Professor professor) {
        professor.setId(id);
        try {
            professor = service.update(professor);
            if (professor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(professor, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }
        @Operation(summary = "Delete a professor")
        @ApiResponses({
                @ApiResponse(responseCode = "204", description = "No Content"),
                @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
        })
        @DeleteMapping(path = "/{professor_id}")
        public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        
        @Operation(summary = "Delete all professors")
        @ApiResponses({
                @ApiResponse(responseCode = "204", description = "No Content")
        })
        @DeleteMapping
        public ResponseEntity<Void> deleteAll() {
            service.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }


