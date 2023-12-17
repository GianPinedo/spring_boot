package com.blogapp.apis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;

import com.blogapp.apis.entities.Comment;
import com.blogapp.apis.entities.Post;
import com.blogapp.apis.entities.User;
import com.blogapp.apis.exceptions.ResourceNotFindException;
import com.blogapp.apis.payload.CommentDto;
import com.blogapp.apis.repositories.CommentRepository;
import com.blogapp.apis.repositories.PostRepository;
import com.blogapp.apis.repositories.UserRepository;
import com.blogapp.apis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto creteComment(CommentDto commentDto,
			Integer postId, Integer userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() ->{
			return new ResourceNotFindException("User", "userId", userId);
		});
		
		
		Post post = postRepository.findById(postId).orElseThrow(() ->{
			return new ResourceNotFindException("Post", "postId", postId);
		});
		
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		comment.setCommentDate(new Date());
		
		Comment newAddedComment = commentRepository.save(comment);
		user.getCommentList().add(newAddedComment);
		post.getCommentList().add(newAddedComment);
		
		postRepository.save(post);
		userRepository.save(user);
		return modelMapper.map(newAddedComment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto,
			Integer postId, Integer UserId) {
		
		return null;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
			return new ResourceNotFindException("Comment","commentId", commentId);
		});
		commentRepository.delete(comment);
	}

	@Override
	public CommentDto getComment(Integer commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
			return new ResourceNotFindException("Comment","commentId", commentId);
		});
		
		return modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllCommentByPostId(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDto> getAllCommentByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDto> getAllCommentByPostIdAndUserId(Integer postId, Integer UserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDto> getAllComment() {
		List<Comment> commentList = commentRepository.findAll();
		List<CommentDto> commentDtoList = commentList.stream().map((comment) -> {
			return modelMapper.map(comment, CommentDto.class);
		}).collect(Collectors.toList());
		
		return commentDtoList;
	}

}
