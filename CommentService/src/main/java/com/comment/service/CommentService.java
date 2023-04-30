package com.comment.service;

import java.util.List;

import com.comment.entity.Comment;
import com.comment.exception.CommentException;
import com.comment.exception.UserException;

public interface CommentService {

	public Comment createComment(Comment comment);
	
	public Comment deleteComment(int blogId, int commentId)throws CommentException,UserException;
	
	public Comment getCommentById(int commentId)throws CommentException;
	
	public List<Comment> getCommentByUserId(int userId)throws CommentException;
	
	public List<Comment> getCommentByBlogId(int blogId)throws CommentException;
	
}
