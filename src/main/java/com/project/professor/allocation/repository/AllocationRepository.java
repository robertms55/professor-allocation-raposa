package com.project.professor.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

	List<Allocation> findByProfessor(Professor professor);
	List <Allocation> findByCourse (Course course);
	
	
}
