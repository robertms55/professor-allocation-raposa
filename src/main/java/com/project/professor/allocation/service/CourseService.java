package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository repository;

	public CourseService(CourseRepository repository) {

		this.repository = repository;

	}

	public Course findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Course> findAll(String name) {

		if (name == null) {
			return repository.findAll();
		} else {
			return repository.findByNameContainingIgnoreCase(name);
		}


	}

	public Course save(Course course) {

		course.setId(null);
		return repository.save(course);


	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id != null && repository.existsById(id)) {
			return repository.save(course);
		} else {
			return null;
		}

	}

	public void deleteById(Long id) {


		if (id != null && repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public void deleteAll() {

		repository.deleteAllInBatch();

	}
}
