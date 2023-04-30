package com.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	public User updateUser(int userId,User user) throws UserException {
		
		Optional<User> update = userDao.findById(userId);
		
		if(update.isPresent()) {
			
			User up =  update.get();
			
			up.setFirstName(user.getFirstName());
			up.setLastName(user.getLastName());
			up.setEmail(user.getEmail());
			up.setAge(user.getAge());
			up.setGender(user.getGender());
			up.setPassword(user.getPassword());
			
			return userDao.save(up);
			
		}
		
		else
			
		 throw new UserException("User Not Found By This ID :"+ userId);
		
		
	}

	@Override
	public User deleteUser(int userId) throws UserException {
		
Optional<User> update = userDao.findById(userId);
		
		if(update.isPresent()) {
			
			User up =  update.get();
	        
			userDao.delete(up);
			
			return up;
	
		}
		
		else
			
		 throw new UserException("User Not Found By This ID :"+ userId);
		
	}

}
