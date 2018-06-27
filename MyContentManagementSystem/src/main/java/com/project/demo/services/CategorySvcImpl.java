package com.project.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Category;
import com.project.demo.repository.CategoryRepository;

@Service
public class CategorySvcImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void save(Category category) {
		
		category.setSlug(category.getName());
		
		category.setDeleteLock(0);
		
		categoryRepository.save(category);
	}

	@Override
	public Category findById(Integer id) {

		return categoryRepository.findById(id);
	}

	@Override
	public void update(Category category) {

		category.setSlug(category.getName());
		
		categoryRepository.update(category.getId(), category.getName(), category.getSlug(), category.getModule(), category.getParentId());

	}

	@Override
	public List<Category> findAll() {

		return categoryRepository.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		
		categoryRepository.delete(id);
		
	}
	
	public List<Category> findByModuleId(int module_id) {
		return categoryRepository.findByModuleId(module_id);
	}

	@Override
	public List<Category> getModuleCategoriesByName(String moduleName) {
		return categoryRepository.getModuleCategoriesByName(moduleName);
	}

}
