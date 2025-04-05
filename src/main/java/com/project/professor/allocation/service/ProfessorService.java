package com.project.professor.allocation.service;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {
	private final DepartmentService department;
	private final ProfessorRepository repository;

	public ProfessorService(ProfessorRepository repository, DepartmentService department) {

		this.repository = repository;
		this.department = department;
	}

	public Professor findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		if (repository.existsById(id))
			repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAllInBatch();
	}

	public Professor save(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		if (repository.existsById(professor.getId())) {
			return saveInternal(professor);
		} else {
			return null;
	}}

	private Professor saveInternal(Professor professor) {
		professor = repository.save(professor);
		Department departament = department.findById(professor.getDepartment().getId());
		professor.setDepartment(departament);
		return professor;

	}

}
