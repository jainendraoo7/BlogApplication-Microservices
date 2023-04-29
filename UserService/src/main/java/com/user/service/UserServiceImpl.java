package com.user.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.user.entity.Blogs;
import com.user.entity.Comment;
import com.user.entity.User;
import com.user.exception.UserException;
import com.user.external.CommentService;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CommentService commentservice;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	
	@Override
	public User RegisterUser(User user) {
	
		return userDao.save(user);
		
	}
	
	
	@Override
	public List<User> getUsers() {
		
		return userDao.findAll();
		
	}

	@Override
	public User getUserById(int userId) throws UserException {
		
		User user =  userDao.findById(userId).orElseThrow(() -> new UserException("User Not Found By This ID "+ userId));
		
		  try {
		        Blogs[] blogsOfUser = restTemplate.getForObject(
		                "http://BLOG-SERVICE/blogs/userId/" + user.getUserId(), 
		                Blogs[].class);

		        List<Blogs> blogs = Arrays.asList(blogsOfUser);

		        for (Blogs blog : blogs) {
		            List<Comment> comments = commentservice.getCommentByBlogId(blog.getBlogId());
		           
		            blog.setComments(comments);
		        }

		        user.setBlogs(blogs);
		    } catch (RestClientException ex) {
		        logger.error("Error calling blog service: {}", ex.getMessage());
		    }

		    return user;
	}

	@Override
	public User updateUser(int userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(int userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
