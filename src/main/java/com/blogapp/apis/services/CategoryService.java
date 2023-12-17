package com.blogapp.apis.services;

import java.util.List;

import com.blogapp.apis.entities.Category;
import com.blogapp.apis.payload.CategoryDto;

public interface CategoryService {
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);
	
	//getAll
	public List<CategoryDto> getAllCategories();
	
}
