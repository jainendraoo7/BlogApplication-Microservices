package com.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.exception.UserException;
import com.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/add")
	public ResponseEntity<User> RegisterUserHandller(@RequestBody User user){
		
		User u1 = userService.RegisterUser(user);
		
		return new ResponseEntity<>(u1, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsersHandller(){
		
		List<User> u1 = userService.getUsers();
		
		return new ResponseEntity<>(u1, HttpStatus.ACCEPTED);
	}
	
	int retryCount = 1;	
	
	@GetMapping("/userId/{userId}")
//	@CircuitBreaker(name = "blogCommentBreaker", fallbackMethod = "BlogCommentFallback")
	@Retry(name = "blogCommentService", fallbackMethod = "BlogCommentFallback")
	public ResponseEntity<User> getUserByIdHandller(@PathVariable Integer userId) throws UserException{
        
		logger.info("Retry count: {}", retryCount);
		
		retryCount++;
		
		User u1 = userService.getUserById(userId);
		
		return new ResponseEntity<>(u1, HttpStatus.ACCEPTED);
	}

	
	public ResponseEntity<User> BlogCommentFallback(Integer userId, Exception ex){
		
		
		ex.printStackTrace();
		
		User user = User.builder().userId(123).email("dummy@gmail.com").firstName("dummy").lastName("singh").age(24).password("dummy1234").gender("male").build();
		
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
}
