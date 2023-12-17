package com.blogapp.apis;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blogapp.apis.configurations.AppConstants;
import com.blogapp.apis.entities.Role;
import com.blogapp.apis.repositories.RoleRepository;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

//https://www.javaguides.net/2023/03/spring-boot-3-rest-api-documentation.html
//http://localhost:9191/swagger-ui/index.html


@io.swagger.v3.oas.annotations.security.SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
)

@SpringBootApplication
public class BlogApplication implements CommandLineRunner{

	@Autowired
	public RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_ROLE_ID);
			role1.setRoleName("ROLE_ADMIN");
			
			Role role2 = new Role();
			role1.setId(AppConstants.USER_ROLE_ID);
			role1.setRoleName("ROLE_USER");
			
			List<Role> roleList = List.of(role1, role2);
			this.roleRepository.saveAll(roleList);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	
	  @Bean public OpenAPI nexusOpenApi() { return new OpenAPI() .info(new
	  io.swagger.v3.oas.models.info.Info().title("Title 1")
	  .description("Description 1") .version("Version 1") .license(new
	  io.swagger.v3.oas.models.info.License().name("(C) Copyright xxx").url(
	  "http://www.example.com"))) .security(Collections.singletonList(new
	  io.swagger.v3.oas.models.security.SecurityRequirement().addList(
	  "Authorization"))) .externalDocs(new
	  io.swagger.v3.oas.models.ExternalDocumentation()); }
	 
}
