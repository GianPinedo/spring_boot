package com.blogapp.apis.services;

import java.util.List;

import com.blogapp.apis.payload.PostDto;
import com.blogapp.apis.payload.PostResponse;

public interface PostService {

	//create
	public PostDto createPost(PostDto postDto, Integer userId, Integer CategoryId);
	
	//update
	public PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	public void deletePost(Integer postId);
	
	//get
	public PostDto getPost(Integer postId);
	
	//get
	public List<PostDto> getAllPostByUserId(Integer userId);
	
	//get
	public List<PostDto> getAllPostByCategoryId(Integer categoryId);
	
	//getAll
	public List<PostDto> getAllPost();
	
	//getAll
	public PostResponse getAllPostWithPagination(Integer pageNumber, Integer pageSize,
			String sortBy, String sortDirection);
	
	//getAll post by search title keyword
	public List<PostDto> searchByTitleContaining(String keyword);
	
	//getAll post by search content keyword
	public List<PostDto> searchByContentKeyword(String keyword);
}
