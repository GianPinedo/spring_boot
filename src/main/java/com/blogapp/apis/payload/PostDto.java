package com.blogapp.apis.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blogapp.apis.entities.Category;
import com.blogapp.apis.entities.Comment;
import com.blogapp.apis.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Integer postId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	@NotEmpty
	private String imageName;
	
	private Date date;
	
	private UserDTO user;
	
	private CategoryDto category;
	
	private List<CommentDto> commentDtoList = new ArrayList<>();
}
