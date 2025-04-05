package com.project.professor.allocation.service;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private DepartmentRepository repository;

	public DepartmentService(DepartmentRepository repository) {

		this.repository = repository;

	}

	public Department findById(Long id) {
		return repository.findById(id).orElse(null);

	}

}
