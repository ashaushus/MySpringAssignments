package com.project.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.demo.models.Page;
import com.project.demo.repository.PageRepository;

public class PageSvcImpl implements PageService {

	@Autowired
	PageRepository pageRepository;
	
	@Override
	public void save(Page page) {
		pageRepository.save(page);
	}

	@Override
	public List<Page> findAll() {

		return pageRepository.findAll();
	}

	@Override
	public Page findById(Integer pId) {
		// TODO Auto-generated method stub
		return pageRepository.findOne(pId);
	}
	
	@Override
	public void update(Page p1) {
		pageRepository.save(p1);
	}
	
	@Override
	public void delete(Page page) {
		pageRepository.delete(page.getId());
		
	}

	@Override
	public void deleteById(int id) {
		pageRepository.delete(id);
		
	}

}
