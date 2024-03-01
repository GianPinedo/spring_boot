package com.blogapp.apis.services.impl;

import java.applet.AppletContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapp.apis.configurations.AppConstants;
import com.blogapp.apis.entities.Role;
import com.blogapp.apis.entities.User;
import com.blogapp.apis.exceptions.ResourceNotFindException;
import com.blogapp.apis.payload.UserDTO;
import com.blogapp.apis.repositories.RoleRepository;
import com.blogapp.apis.repositories.UserRepository;
import com.blogapp.apis.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = convertUserDtoIntoUserEntity(userDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		return convertUserEntityIntoUserDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() ->{
			return new ResourceNotFindException("User", "userId", userId);
		});
		
		user = this.convertUserDtoIntoUserEntity(userDto);
		User updatedUser = this.userRepository.save(user);
		
		return this.convertUserEntityIntoUserDto(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->{
			return new ResourceNotFindException("User","id",userId);
		});
		
		return this.convertUserEntityIntoUserDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList = this.userRepository.findAll();
		List<UserDTO> userDTOList = userList.stream().map(user -> 
			this.convertUserEntityIntoUserDto(user)
		).collect(Collectors.toList());

		return userDTOList;
	}

	@Override
	public void delteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->{
			return new ResourceNotFindException("User","id",userId);
		});
		
		this.userRepository.delete(user);
	}
	
	public User convertUserDtoIntoUserEntity(UserDTO userDTO) {
		User user = new User();
		/*
		 * user.setId(userDTO.getId());
		 *  user.setName(userDTO.getName());
		 * user.setPassword(userDTO.getPassword());
		 *  user.setEmail(userDTO.getEmail());
		 * user.setAbout(userDTO.getAbout());
		 */
		user = this.modelMapper.map(userDTO, User.class);
		return user;
	}
	
	public UserDTO convertUserEntityIntoUserDto(User user) {
		UserDTO userDTO = new UserDTO();
		/*
		 * userDTO.setId(user.getId());
		 *  userDTO.setName(user.getName());
		 * userDTO.setEmail(user.getEmail()); 
		 * userDTO.setPassword(user.getPassword());
		 * userDTO.setAbout(user.getAbout());
		 */
		userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO RegistereUser(UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//Role role = roleRepository.findById(AppConstants.USER_ROLE_ID).get();
		//user.getRoleSet().add(role);
		User newUser = userRepository.save(user);
		return modelMapper.map(newUser, UserDTO.class);
	}
	
}
