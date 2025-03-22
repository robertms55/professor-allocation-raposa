package com.project.professor.allocation.controller;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.professor.allocation.entity.Allocation;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocations = new ArrayList<>();

		Allocation alloc = new Allocation();
		alloc.setDay(DayOfWeek.SATURDAY);
		alloc.setStart(Time.valueOf(LocalTime.of(8, 00)));
		alloc.setEnd(Time.valueOf(LocalTime.of(17, 00)));

		allocations.add(alloc);

		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
		if (id % 2 == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Allocation allocation = new Allocation();
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> save(@RequestBody Allocation allocation) {
		allocation.setId(550l);
	    return new ResponseEntity<>(allocation, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id") Long id, @RequestBody Allocation allocation) {
		allocation.setDay(DayOfWeek.TUESDAY);
        return new ResponseEntity<>(allocation, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{allocation_id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "allocation_id") Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
