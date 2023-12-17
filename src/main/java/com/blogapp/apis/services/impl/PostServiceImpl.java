package com.blogapp.apis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapp.apis.entities.Category;
import com.blogapp.apis.entities.Post;
import com.blogapp.apis.entities.User;
import com.blogapp.apis.exceptions.ResourceNotFindException;
import com.blogapp.apis.payload.PostDto;
import com.blogapp.apis.payload.PostResponse;
import com.blogapp.apis.repositories.CategoryRepository;
import com.blogapp.apis.repositories.PostRepository;
import com.blogapp.apis.repositories.UserRepository;
import com.blogapp.apis.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepository.findById(userId).orElseThrow(()->{
			return new ResourceNotFindException("User", "userId", userId);
		});
		
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
			return new ResourceNotFindException("Category", "categoryId", categoryId);
		});
		
		Post post = modelMapper.map(postDto, Post.class);
		post.setUser(user);
		post.setDate(new Date());
		post.setCategory(category);
		
		Post newPost = postRepository.save(post);
		
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> {
			return new ResourceNotFindException("Post", "postId", postId);
		});
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = postRepository.save(post);
		
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> {
			return new ResourceNotFindException("Post", "postId", postId);
		});
		
		postRepository.delete(post);
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> {
			return new ResourceNotFindException("Post", "PostId", postId);
		});
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> postList = postRepository.findAll();
		
		List<PostDto> postDtoList = postList.stream().map((post) -> {
			return modelMapper.map(post, PostDto.class);
		}).collect(Collectors.toList());
		
		return postDtoList;
	}

	@Override
	public List<PostDto> getAllPostByUserId(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> {
			return new ResourceNotFindException("User","userId",userId);
		});
		
		List<Post> postList = postRepository.findByUser(user);
		List<PostDto> postDtoList = postList.stream().map((post)->{
			return modelMapper.map(post, PostDto.class);
		}).collect(Collectors.toList());
		
		return postDtoList;
	}

	@Override
	public List<PostDto> getAllPostByCategoryId(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->{
			 return new ResourceNotFindException("Category", "categoryId", categoryId);
		});
		
		List<Post> postList = postRepository.findByCategory(category);
		List<PostDto> postDtoList = postList.stream().map((post) -> {
			return modelMapper.map(post, PostDto.class);
		}).collect(Collectors.toList());
		
		return postDtoList;
	}

	@Override
	public PostResponse getAllPostWithPagination(Integer pageNumber, Integer pageSize,
			String sortBy, String sortDirection) {
		
		Sort sort = sortDirection.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> postPage = postRepository.findAll(pageable);
		
		List<Post> postList = postPage.getContent();
		
		List<PostDto> postDtoList = postList.stream().map((post) -> {
			return modelMapper.map(post, PostDto.class);
		}).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setIsLastPage(postPage.isLast());
		postResponse.setPageNumber(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalElements(postPage.getTotalElements());
		postResponse.setTotalPages(postPage.getTotalPages());
		
		postResponse.setPostDtoList(postDtoList);
		
		return postResponse;
	}

	@Override
	public List<PostDto> searchByTitleContaining(String keyword) {
		List<Post> postList = postRepository.findByTitleContaining(keyword);
		
		List<PostDto> searchResultPostDto = postList.stream().map((post) -> {
			return modelMapper.map(post, PostDto.class);
		}).collect(Collectors.toList());
		
		return searchResultPostDto;
	}

	@Override
	public List<PostDto> searchByContentKeyword(String keyword) {
		List<Post> postList = postRepository.searchAllByContentKeyword(keyword);
		List<PostDto> searchResultPostDto = postList.stream().map((post) -> {
			return modelMapper.map(post, PostDto.class);
		}).collect(Collectors.toList());
		
		return searchResultPostDto;
	}

}
