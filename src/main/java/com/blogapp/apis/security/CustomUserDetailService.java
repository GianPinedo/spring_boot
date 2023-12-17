package com.blogapp.apis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapp.apis.entities.User;
import com.blogapp.apis.exceptions.ResourceNotFindException;
import com.blogapp.apis.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElseThrow(()-> {
					return new ResourceNotFindException("User", "Email : "+ username , null);
				});
		
		return user;
	}

}
