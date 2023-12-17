package com.blogapp.apis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapp.apis.repositories.UserRepository;

@SpringBootTest
class BlogApplicationTests {
	@Autowired
	UserRepository userRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test 
	public void checkClassNameForRepository() {
		String className = userRepository.getClass().getName();
		String packageName = userRepository.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
		
	}

}
