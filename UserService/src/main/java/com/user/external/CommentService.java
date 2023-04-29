package com.user.external;

import java.util.List;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.entity.Comment;

@FeignClient(name = "COMMENT-SERVICE")
public interface CommentService {

	@GetMapping("/comments/blogId/{blogId}")
	List<Comment> getCommentByBlogId(@PathVariable Integer blogId);
	
}
