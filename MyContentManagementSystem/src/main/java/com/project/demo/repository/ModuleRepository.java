package com.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.models.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer>{
	
}
