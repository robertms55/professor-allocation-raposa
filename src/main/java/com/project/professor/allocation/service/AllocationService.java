package com.project.professor.allocation.service;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository repository;

	public AllocationService(AllocationRepository repository) {

		this.repository = repository;

	}

}
