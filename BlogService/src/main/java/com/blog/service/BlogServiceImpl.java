package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Blogs;
import com.blog.entity.Comment;
import com.blog.exception.BlogException;
import com.blog.exception.UserException;
import com.blog.external.CommentService;
import com.blog.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {

	
	@Autowired
	private BlogRepository blogDao;
	
	@Autowired
	private CommentService commentService;
	
	@Override
	public Blogs createBlog(Blogs blogs) {
		
		return blogDao.save(blogs);
		
	}

	@Override
	public Blogs deleteBlog(int blogId, int userId) throws BlogException, UserException {
	
		Optional<Blogs> user = blogDao.findById(userId);
		
		if(user.isPresent()) {
			
			Optional<Blogs> blog = blogDao.findById(blogId);			
			
			if(blog.isPresent()) {
				
				Blogs b = blog.get(); 
				
			    blogDao.delete(b);
				
			    return b;
			}
			else {
				
				throw new BlogException("Blog Not Found By This ID :" + blogId);
				
			}
	
		}
		else
			
			throw new UserException("User Not Found By This ID :" + userId);
		
		
	}

	@Override
	public Blogs getBlogsById(int blogId) throws BlogException {
		
		Blogs blog =  blogDao.findById(blogId).orElseThrow(() -> new BlogException("Blog Not Found By This ID :"+ blogId));
		
		
		  List<Comment> cmt = commentService.getComments(blog.getBlogId());
		
		 blog.setComments(cmt);
	 	
		return blog;
		
	}

	@Override
	public List<Blogs> getAllBlogsByUserId(int userId) throws BlogException {
		
      List<Blogs> list =  blogDao.GetByUserId(userId);
		
      if(list.isEmpty()) {
    	  
    	  throw new BlogException("Blog Not Found");
    	  
      }
      
      else 
    	  
    	  for(Blogs blog: list) {
    		  
    		  List<Comment> cmt = commentService.getComments(blog.getBlogId());
    	 
    		   blog.setComments(cmt);
    	  }
    	  
    	  return list;
      
      
	}

	
	
}
