package com.user.service;

import java.util.List;

import com.user.entity.User;
import com.user.exception.UserException;

public interface UserService {

	public User RegisterUser(User user);
	
    public List<User> getUsers();
	
    public User getUserById(int userId)throws UserException;
    
	public User updateUser(int userId,User user)throws UserException;
	
	public User deleteUser(int userId)throws UserException;
	
	
}
