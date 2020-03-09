package com.mycompany.commentstore.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycompany.model.Comment;

public interface CommentService {
	String put(Comment model) throws IOException;
	
	List<Comment> list(String pageId) throws IOException;
	
	Page<Comment> list(String pageId, Pageable pageable) throws IOException;
	
	Comment get(String id);
		
	List<Comment> listSpamComments(String pageId) throws IOException;
	
	void delete(String id);
}
