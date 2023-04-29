package com.blog.controller;

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

import com.blog.entity.Blogs;
import com.blog.exception.BlogException;
import com.blog.service.BlogService;

@RestController
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;
	

	@PostMapping("/add")
	public ResponseEntity<Blogs> createBlog(@RequestBody Blogs blogs){
		
		Blogs b1 = blogService.createBlog(blogs);
		
		return new ResponseEntity<>(b1, HttpStatus.CREATED);
	
	}
	
	@GetMapping("/blogId/{blogId}")
	public ResponseEntity<Blogs> getBlogById(@PathVariable Integer blogId) throws BlogException{
		
		Blogs b1 = blogService.getBlogsById(blogId);
		
		return new ResponseEntity<>(b1, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<List<Blogs>> getBlogByUserId(@PathVariable Integer userId) throws BlogException{
		
		List<Blogs> b1 = blogService.getAllBlogsByUserId(userId);
		
		return new ResponseEntity<>(b1, HttpStatus.ACCEPTED);
		
	}
	
	
	
}
