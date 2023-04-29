package com.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comment.entity.Comment;
import com.comment.exception.CommentException;
import com.comment.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/add")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
		
		Comment c1 = commentService.createComment(comment);
		
		return new ResponseEntity<>(c1, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/commentId/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable Integer commentId) throws CommentException{
		
		Comment c1 = commentService.getCommentById(commentId);
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<List<Comment>> getCommentByUserId(@PathVariable Integer userId) throws CommentException{
		
		List<Comment> c1 = commentService.getCommentByUserId(userId);
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/blogId/{blogId}")
	public ResponseEntity<List<Comment>> getCommentByBlogId(@PathVariable Integer blogId) throws CommentException{
		
		List<Comment> c1 = commentService.getCommentByBlogId(blogId);
		
		return new ResponseEntity<>(c1, HttpStatus.ACCEPTED);
		
	}
	
	
	
}
