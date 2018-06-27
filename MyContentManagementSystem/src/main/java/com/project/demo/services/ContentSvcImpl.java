package com.project.demo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.demo.models.Content;
import com.project.demo.repository.ContentRepository;

@Service
public class ContentSvcImpl implements ContentService{

	@Autowired
	private ContentRepository contentRepository;
	
	final int MODULE_ID = 3;
	
	private final String UPLOAD_FILE_PATH = "file/module/content";//"src/main/resources/static/file/module/content";

	public void save(Content content, MultipartFile file) {
		
		if (!file.isEmpty()) {
			System.out.println("not empty file");	
			
		} else {
			System.out.println("empty file");
		}
		// slugify
		content.setSlug(content.getTitle());
		
		content.setStatus(1);
		Date date = new Date();
		content.setCreatedAt(date);
		
		contentRepository.save(content);
	}
	
	
	public Content findByIdForShow(Long id) {
		
		Content content = contentRepository.findByIdForShow(id);
		
		return content;
	}
	
	public Content findById(Long id) {
		
		Content content = contentRepository.findById(id);
		
		return content;
	}
	
	public void update(Content content, MultipartFile file, Map<String, String[]> parameterMap) {
		
		
		if (!file.isEmpty()) {
			System.out.println("not empty file");	

		} else {
			content.setFileName(parameterMap.get("old_file")[0]);
			System.out.println("empty file");
		}
		// slugify
		content.setSlug(content.getTitle());
		
		// content status
		content.setStatus(1);
		
		//Date date = new Date();
	   //content.setCreatedAt(date);
		contentRepository.update(content.getId(), content.getTitle(), content.getSlug(),
				content.getContent(), content.getCategory(), content.getFileName());
	}
	
	public Page<Content> findAll(Pageable pageRequest) {
        return contentRepository.findAll(pageRequest);
    }


	public void deleteById(Long id) throws Exception {

		// delete content picture if exist
		
		Content content = contentRepository.findOne(id);
		
		if(content == null) {
			throw new Exception("");
		}
		
		if(content.getFileName() != "" && content.getFileName() != null) {
			// delete file from filesystem
			Path path = Paths.get(UPLOAD_FILE_PATH);
			
			if( Files.exists(path.resolve(content.getFileName()))) {
				try {
					Files.delete(path.resolve(content.getFileName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		contentRepository.delete(id);
		
	}


	@Override
	public Page<Content> getLatestContent(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
	


	