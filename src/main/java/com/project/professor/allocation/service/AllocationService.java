package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository repository;
	private final ProfessorService professorService;
	private final CourseService courseService;
	
	public AllocationService(AllocationRepository repository, ProfessorService professorService, CourseService courseService) {
		this.repository = repository;
		this.professorService = professorService;
		this.courseService = courseService;
	}
	
	public Allocation findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Allocation> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(Long id) {
		if (repository.existsById(id))
			repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAllInBatch();
	}
	
	public Allocation save(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);
	}

	public Allocation update(Allocation allocation) {
		if (repository.existsById(allocation.getId())) {
			return saveInternal(allocation);
		} else {
			return null;
		}
	}
	
	private Allocation saveInternal(Allocation allocation) {
		allocation = repository.save(allocation);
		Professor professor = professorService.findById(allocation.getProfessor().getId());
		allocation.setProfessor(professor);
		Course course = courseService.findById(allocation.getCourse().getId());
		allocation.setCourse(course);
		return allocation;
	}
}