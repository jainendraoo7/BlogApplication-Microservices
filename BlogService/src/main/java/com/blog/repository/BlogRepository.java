package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blogs;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Integer> {

	@Query("select b from Blogs b where b.userId = ?1")
	List<Blogs> GetByUserId(int userId);

	
}
