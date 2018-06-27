package com.project.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.project.demo.models.Category;
import com.project.demo.services.CategoryService;
import com.project.demo.models.Content;
import com.project.demo.services.ContentService;

@Service
public class BlockServiceImp implements BlockService {

	@Autowired
	ContentService contentService;
	
	@Autowired
	CategoryService categoryService;
	
	/*@Cacheable(value="latestContents")*/
/*	@CacheEvict(value="latestContents")*/
	@Override
	public Page<Content> getLatestContents() {
		/*return contentService.getLatestContent(3);*/
		Page<Content> contents = contentService.getLatestContent(3);
		
		System.out.println("getLatestContents: "+contents);
		
		return contents;
		
	}
	
/*@Cacheable(value="contentCategories")*/
	/*@CacheEvict*/
	@Override
	public List<Category> getContentCategories() {
		return categoryService.getModuleCategoriesByName("content");
		
	}
}
