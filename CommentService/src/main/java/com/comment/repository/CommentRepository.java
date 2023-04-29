package com.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c where c.userId = ?1")
	List<Comment> getByUserId(int userId);

	@Query("select c from Comment c where c.blogId = ?1")
	List<Comment> getByBlogId(int blogId);

	
	
}
