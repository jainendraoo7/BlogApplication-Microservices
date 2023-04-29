package com.user.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blogs {

    private int blogId;
	
	private int userId; 
	
	private String title;
	
	private String content;
	
	private List<Comment> comments = new ArrayList<>(); 
	
}

