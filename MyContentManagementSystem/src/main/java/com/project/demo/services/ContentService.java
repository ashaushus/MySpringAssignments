package com.project.demo.services;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.demo.models.Content;

@Service
public interface ContentService {

	public void save(Content content, MultipartFile file);
		
	public void update(Content content, MultipartFile file, Map<String, String[]> parameterMap);
	
	public Page<Content> findAll(Pageable pageRequest);

	public void deleteById(Long id) throws Exception;

	public Page<Content> getLatestContent(int i);
	
}
