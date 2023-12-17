package com.blogapp.apis.payload;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class JWTAuthRequest {

	private String email;
	private String password;
}
