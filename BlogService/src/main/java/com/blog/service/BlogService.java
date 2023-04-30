package com.blog.service;

import java.util.List;

import com.blog.entity.Blogs;
import com.blog.exception.BlogException;
import com.blog.exception.UserException;

public interface BlogService {

	public Blogs createBlog(Blogs blogs);
		
	public Blogs deleteBlog(int blogId, int userId)throws BlogException, UserException; 
	
	public Blogs getBlogsById(int blogId)throws BlogException;
	
	public List<Blogs> getAllBlogsByUserId(int userId)throws BlogException;
	
	
}
