package com.blogapp.apis.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentDto {

	private Integer id;
	
	private String content;
	
	private Date commentDate;

}
