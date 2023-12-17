package com.blogapp.apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blogapp.apis.configurations.AppConstants;
import com.blogapp.apis.payload.APIResponse;
import com.blogapp.apis.payload.PostDto;
import com.blogapp.apis.payload.PostResponse;
import com.blogapp.apis.repositories.CategoryRepository;
import com.blogapp.apis.services.FileService;
import com.blogapp.apis.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {
		PostDto createdPostDto = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPostDto = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}

	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByUserId(@PathVariable("userId") Integer id) {
		List<PostDto> postDtoList = postService.getAllPostByUserId(id);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}

	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByCategoryId(@PathVariable() Integer categoryId) {
		List<PostDto> postDtoList = postService.getAllPostByCategoryId(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}

	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postDto = postService.getPost(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@GetMapping("/posts_with_page")
	public ResponseEntity<PostResponse> getPostByIdPagination(
			@RequestParam(name = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY_FIELD_NAME, required = false) String sortBy,
			@RequestParam(name = "sortDirection", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDirection) {
		PostResponse postResponse = postService.getAllPostWithPagination(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("posts")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> postDtoList = postService.getAllPost();

		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}

	@DeleteMapping("posts/{postId}")
	public ResponseEntity<APIResponse> deletePostById(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<APIResponse>(new APIResponse("Post deleted successfully", true, HttpStatus.OK),
				HttpStatus.OK);
	}

	@GetMapping("search/posts/title/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitleKeyword(@PathVariable String keywords) {
		List<PostDto> postDtoList = postService.searchByTitleContaining(keywords);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}

	@GetMapping("search/posts/content/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByContentKeyword(@PathVariable String keywords) {
		List<PostDto> postDtoList = postService.searchByContentKeyword("%" + keywords + "%");
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}

	@PostMapping("/post/file-upload/{postId}")
	@ResponseBody
	public ResponseEntity<String> fileUpload(@PathVariable() Integer postId,
			@RequestParam("file_key") MultipartFile multipartFile) {
		PostDto postDto = postService.getPost(postId);

		try {
			if (multipartFile.isEmpty()) {
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("emplty file not allowed");
			}
			
			boolean isFileUploaded = fileService.uploadFile(multipartFile);
			if (isFileUploaded) {
				postDto.setImageName(multipartFile.getOriginalFilename());
				postService.updatePost(postDto, postId);
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/")
						.path(multipartFile.getOriginalFilename()).toUriString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong ###");
	}

}
