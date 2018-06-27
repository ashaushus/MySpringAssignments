package com.project.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.project.demo.models.Category;
import com.project.demo.models.Content;


public interface BlockService {

	public Page<Content> getLatestContents();
	
	public List<Category> getContentCategories();
}
