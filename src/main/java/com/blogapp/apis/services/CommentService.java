package com.blogapp.apis.services;

import java.util.List;

import com.blogapp.apis.payload.CommentDto;

public interface CommentService {

	public CommentDto creteComment(CommentDto commentDto, Integer postId, Integer UserId);

	public CommentDto updateComment(CommentDto commentDto, Integer postId, Integer UserId);

	public void deleteComment(Integer commentId);

	public CommentDto getComment(Integer commentId);
	
	public List<CommentDto> getAllComment();

	public List<CommentDto> getAllCommentByPostId(Integer postId);

	public List<CommentDto> getAllCommentByUserId(Integer userId);

	public List<CommentDto> getAllCommentByPostIdAndUserId(
			Integer postId, Integer UserId);

}
