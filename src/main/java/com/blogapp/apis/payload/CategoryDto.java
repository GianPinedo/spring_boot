package com.blogapp.apis.payload;

import org.hibernate.annotations.NotFound;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min=4, max=50)
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=5 , max=100, message="category Descripation must between 5 to 50 char")
	private String categoryDescription;
}
