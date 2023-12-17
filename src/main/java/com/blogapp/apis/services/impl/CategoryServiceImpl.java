package com.blogapp.apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.apis.entities.Category;
import com.blogapp.apis.exceptions.ResourceNotFindException;
import com.blogapp.apis.payload.CategoryDto;
import com.blogapp.apis.payload.UserDTO;
import com.blogapp.apis.repositories.CategoryRepository;
import com.blogapp.apis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		category = categoryRepository.save(category);
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// Category category = modelMapper.map(categoryDto, Category.class);
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
			return new ResourceNotFindException("Category", "categoryId", categoryId);
		});
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());

		category = categoryRepository.save(category);
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
			return new ResourceNotFindException("Category", "categoryId", categoryId);
		});

		categoryRepository.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
			return new ResourceNotFindException("Category", "categoryId", categoryId);
		});

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categoryList = categoryRepository.findAll();

		List<CategoryDto> userDtoList = categoryList.stream().map(category ->
					this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
		
		return userDtoList;
	}

}
