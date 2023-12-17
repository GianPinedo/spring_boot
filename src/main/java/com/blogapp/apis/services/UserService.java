package com.blogapp.apis.services;

import java.util.List;

import com.blogapp.apis.payload.*;

public interface UserService {
	
	public UserDTO RegistereUser(UserDTO userDto);
	public UserDTO createUser(UserDTO userDto);
	public UserDTO updateUser(UserDTO userDto, Integer userId);
	public UserDTO getUserById(Integer userId);
	public List<UserDTO> getAllUsers();
	public void delteUser(Integer userId);
	
}
