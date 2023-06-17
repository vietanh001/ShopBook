package com.example.cuoiky.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cuoiky.config.SecurityCheckRole;
import com.example.cuoiky.model.Book;
import com.example.cuoiky.model.Comment;
import com.example.cuoiky.service.BookService;
import com.example.cuoiky.service.CommentService;

@Controller
public class CommentController {
	
	private final CommentService commentService;
	
	private final BookService bookService;
	
	private final SecurityCheckRole checkRole;

	public CommentController(CommentService commentService, BookService bookService, SecurityCheckRole checkRole) {
		super();
		this.commentService = commentService;
		this.bookService = bookService;
		this.checkRole = checkRole;
	}
	
	@PostMapping("/save/comment/{id}")
	public String saveComment(@ModelAttribute("comments") Comment comment, @PathVariable Long id) {
		Comment comment2 = new Comment();
		comment2.setComment(comment.getComment());
		comment2.setStar(comment.getStar());
		comment2.setUserId(checkRole.getUserId());
		comment2.setTime(LocalDateTime.now());
		comment2.setBook_n((Book)bookService.findById(id));
		comment2.setUsername(checkRole.getUsNa());
		commentService.saveComment(comment2); 
		return "redirect:/";
	}
}
