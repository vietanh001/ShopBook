package com.example.cuoiky.service;

import org.springframework.stereotype.Service;

import com.example.cuoiky.model.Comment;
import com.example.cuoiky.repository.CommentRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}
}
