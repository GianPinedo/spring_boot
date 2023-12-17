package com.blogapp.apis.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private String content;
	
	private Date commentDate;
	
	@ManyToOne()
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
