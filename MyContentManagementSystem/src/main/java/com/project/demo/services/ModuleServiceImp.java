package com.project.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Module;
import com.project.demo.repository.ModuleRepository;

@Service
public class ModuleServiceImp implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	@Override
	public List<Module> findAll() {

		return moduleRepository.findAll();
	}

}