package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blogs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int blogId;
	
	private int userId;
	
	private int commentId; 
	
	private String title;
	
	private String content;
	
	@Transient
	private List<Comment> comments = new ArrayList<>();
}


