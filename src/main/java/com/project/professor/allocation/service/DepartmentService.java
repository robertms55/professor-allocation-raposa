package com.project.professor.allocation.service;

import java.util.List;

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

	public List<Department> findAll(String name) {
		if (name == null) {
			return repository.findAll();
		} else {
			return repository.findByNameContainingIgnoreCase(name);
		}
	}

	public Department save(Department department) {
		department.setId(null);
		return repository.save(department);
	}

	public Department update(Department department) {
		Long id = department.getId();
		if (id != null && repository.existsById(id)) {
			return repository.save(department);
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
