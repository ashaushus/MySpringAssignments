package com.project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.demo.models.Category;
import com.project.demo.services.CategoryService;

@Controller
public class PageController {
	
	@Autowired
	private CategoryService categoryService;
	
	private final String MODULE_TEMPLATE_ROOT = "site/module/page/";
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {

		
		int categoryId = 1;
		Category category = categoryService.findById(categoryId);
		
		model.addAttribute("category", category);
		
		return MODULE_TEMPLATE_ROOT + "home";
	}

}
