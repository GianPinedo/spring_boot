package com.blogapp.apis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogapp.apis.entities.Category;
import com.blogapp.apis.entities.Post;
import com.blogapp.apis.entities.User;
import com.blogapp.apis.payload.PostDto;

public interface PostRepository extends JpaRepository<Post, Integer>{

	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	
	public List<Post> findByTitleContaining(String keyword);
	
	@Query("select alias from Post alias where alias.content like :key")
	public List<Post> searchAllByContentKeyword(@Param("key") String keyword);

}
