package com.blogapp.apis.payload;

import java.util.HashSet;
import java.util.Set;

import com.blogapp.apis.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

	private int id;
	
	@NotEmpty
	@Size(min=4, max=20, message="name lenth must inbetween 4 to 20 char")
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=4, max=10, message="password lenth must inbetween 4 to 10 char")
	//@JsonIgnore
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roleSet = new HashSet();
}

