package com.project.demo.services;

import java.util.List;

import com.project.demo.models.Page;

public interface PageService {

	void save(Page page);

	List<Page> findAll() ;

	Page findById(Integer pId);
	
	void update(Page p1);
	
	void delete(Page page);

	void deleteById(int id);
	
}
