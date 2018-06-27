/**
 * 
 */
package com.project.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	Comment findById(Long id);
	void delete(Long id);
	
	@Query("SELECT c FROM Comment c where c.itemType='CONTENT'")
	List<Comment> findAllContentComment();

	@Modifying
	@Transactional
	@Query("UPDATE Comment c SET c.status=:statusInt WHERE c.id=:id")
	void updateStatus(@Param("id") long id, @Param("statusInt") int statusInt);

	@Query("SELECT c FROM Comment c where c.itemId=:itemId AND c.itemType=:itemType AND c.status=1")
	Set<Comment> findModuleCommentsByIdForShow(@Param("itemType") String itemType, @Param("itemId") Long itemId);

}
