package com.project.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.demo.models.Comment;

@Service
public class CommentSvcImpl implements CommentService {

	@Override
	public void save(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> findAllByType(Class<? extends Class> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllContentComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Comment> findAll(Pageable pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(long id, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Comment> findModuleCommentsByIdForShow(String itemType, Long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
