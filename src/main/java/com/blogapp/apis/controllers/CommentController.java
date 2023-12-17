package com.blogapp.apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.apis.payload.CommentDto;
import com.blogapp.apis.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/comments")
	public ResponseEntity<CommentDto> createNewComment(
			@RequestBody() CommentDto commentDto,
			@PathVariable() Integer userId,
			@PathVariable() Integer postId){
		commentDto = commentService.creteComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/comments/{commentId}")
	public ResponseEntity<CommentDto> getComment(
			@PathVariable() Integer commentId){
		CommentDto commentDto = commentService.getComment(commentId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDto>> getAllComment(){
		List<CommentDto> commentDtoList = commentService.getAllComment();
		return new ResponseEntity<List<CommentDto>>(commentDtoList,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteComment(
			@PathVariable("commentId") Integer commentId){
		commentService.deleteComment(commentId);
		return new ResponseEntity<String>("comment deleted successfully",HttpStatus.OK);
	}
	
}
