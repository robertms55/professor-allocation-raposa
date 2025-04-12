package com.project.professor.allocation.service;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class CourseService {

	private CourseRepository repository;

	public CourseService(CourseRepository repository) {

		this.repository = repository;

	}

	public Course findById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
