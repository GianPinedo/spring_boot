package com.blogapp.apis.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.blogapp.apis.payload.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(length=100, nullable = false)
	private String title;
	
	@Column(length=10000, nullable = false)
	private String content;
	
	private String imageName;
	
	private Date date;
	
	@ManyToOne()
	private User user;
	
	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> commentList = new ArrayList<>();
}
