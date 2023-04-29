package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

   private int commentId; 
	
	private int blogId;
	
	private int userId;
	
	private String content;
	
	
	
}
