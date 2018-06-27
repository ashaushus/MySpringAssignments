package com.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.models.Page;

public interface PageRepository extends JpaRepository<Page, Integer>{

	Page findById(int i);

}
