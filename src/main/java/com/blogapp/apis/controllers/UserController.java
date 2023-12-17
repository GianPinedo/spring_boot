package com.blogapp.apis.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.apis.payload.APIResponse;
import com.blogapp.apis.payload.UserDTO;
import com.blogapp.apis.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("users/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
		userDto = userService.createUser(userDto);
		return new ResponseEntity<UserDTO>(userDto,HttpStatus.CREATED);
	}
	
	@PutMapping("users/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable("userId") Integer id){
		userDto = userService.updateUser(userDto, id);
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}
	
	@DeleteMapping("users/{userId}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable("userId") Integer id){
		userService.delteUser(id);
		return new ResponseEntity<APIResponse>(new APIResponse("User deleted successfully", true, HttpStatus.OK),HttpStatus.OK);
	}
	
	@GetMapping("users")
	//@PreAuthorize("hasAuthority('permission:read') || hasRole('ADMIN')")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return new ResponseEntity<List<UserDTO>>(this.userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("users/{userId}")
	public ResponseEntity<UserDTO> getAllUsers(@PathVariable Integer userId){
		return new ResponseEntity<UserDTO>(this.userService.getUserById(userId),HttpStatus.OK);
	}
}
