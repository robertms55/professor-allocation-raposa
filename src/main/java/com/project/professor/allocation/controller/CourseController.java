package com.project.professor.allocation.controller;

import com.project.professor.allocation.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.project.professor.allocation.entity.Course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Courses")
@RestController
@RequestMapping(path = "/Courses")
public class CourseController {

	private final CourseService service;

	public CourseController(CourseService courseService) {
		super();
		this.service = courseService;
	}

	@Operation(summary = "Find all courses")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<Course> courses = service.findAll(name);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@Operation(summary = "Find a course")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
	})
	@GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> findById(@PathVariable(name = "course_id") Long id) {
		Course course = service.findById(id);
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(course, HttpStatus.OK);
		}
	}

	@Operation(summary = "Save a course")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> save(@RequestBody Course course) {
		try {
			course = service.save(course);
			return new ResponseEntity<>(course, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@Operation(summary = "Update a course")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
	})
	@PutMapping(path = "/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> update(@PathVariable(name = "course_id") Long id,
										 @RequestBody Course course) {
		course.setId(id);
		try {
			course = service.update(course);
			if (course == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@Operation(summary = "Delete a course")
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "No Content"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
	})
	@DeleteMapping(path = "/{course_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "course_id") Long id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Delete all courses")
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "No Content")
	})
	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		service.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}