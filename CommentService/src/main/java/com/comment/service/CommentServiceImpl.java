package com.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comment.entity.Comment;
import com.comment.exception.CommentException;
import com.comment.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentDao;
	
	@Override
	public Comment createComment(Comment comment) {
		
		return commentDao.save(comment);
		
	}

	@Override
	public Comment getCommentById(int commentId) throws CommentException {
		
		return commentDao.findById(commentId).orElseThrow( () -> new CommentException("Comment Not Found By This Id :"+ commentId));
		
	}

	@Override
	public List<Comment> getCommentByUserId(int userId) throws CommentException {
		
		List<Comment> list = commentDao.getByUserId(userId);
		
		if(list.isEmpty()) {
			
			throw new CommentException("Comment Not Found By This Id :" + userId);
			
		}
		
		else
			return list;
		
	}

	@Override
	public List<Comment> getCommentByBlogId(int blogId) throws CommentException {
		
        List<Comment> list = commentDao.getByBlogId(blogId);
		
		if(list.isEmpty()) {
			
			throw new CommentException("Comment Not Found By This Id :" + blogId);
			
		}
		
		else
			return list;
		
		
	}

	
	
	
}
