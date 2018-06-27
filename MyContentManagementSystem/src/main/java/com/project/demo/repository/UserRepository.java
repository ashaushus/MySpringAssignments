package com.project.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.models.Category;
import com.project.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    @Transactional
	 @Query("SELECT c FROM Category c  WHERE c.module.id = :module_id")
	 List<Category> findByModuleId(@Param("module_id") int module_id);
	 
	@Query("SELECT u FROM User u where u.email=:email")
   User findByEmail(@Param("email") String email);
}
