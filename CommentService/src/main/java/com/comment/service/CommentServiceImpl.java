package com.comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comment.entity.Comment;
import com.comment.exception.CommentException;
import com.comment.exception.UserException;
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

	@Override
	public Comment deleteComment(int blogId, int commentId) throws CommentException, UserException {
		
		Optional<Comment> blog = commentDao.findById(blogId);
		
		if(blog.isPresent()) {
			
			Optional<Comment> cmt= commentDao.findById(commentId);
			
			if(cmt.isPresent()) {
				
				Comment c = cmt.get(); 
				
				commentDao.delete(c);
				
				return c;
			}
			else {
				throw new CommentException("Comment Not Found By This ID :"+ commentId);
			}
		}
		else 
		
			throw new UserException("Blog Not Found By This ID :"+ blogId);
		
	}

	
	
	
}
