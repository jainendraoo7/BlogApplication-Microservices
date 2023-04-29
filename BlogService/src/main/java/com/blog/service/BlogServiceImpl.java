package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Blogs;
import com.blog.exception.BlogException;
import com.blog.exception.UserException;
import com.blog.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {

	
	@Autowired
	private BlogRepository blogDao;
	
	@Override
	public Blogs createBlog(Blogs blogs) {
		
		return blogDao.save(blogs);
		
	}

	@Override
	public Blogs deleteBlog(int blogId, int userId) throws BlogException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blogs getBlogsById(int blogId) throws BlogException {
		
		return blogDao.findById(blogId).orElseThrow(() -> new BlogException("Blog Not Found By This ID :"+ blogId));
		
	}

	@Override
	public List<Blogs> getAllBlogsByUserId(int userId) throws BlogException {
		
      List<Blogs> list =  blogDao.GetByUserId(userId);
		
      if(list.isEmpty()) {
    	  
    	  throw new BlogException("Blog Not Found");
    	  
      }
      
      else 
    	  return list;
      
      
	}

	
	
}
