package com.mycompany.commentstore.service;

import java.io.IOException;
import java.util.List;

import com.mycompany.model.Comment;

public interface CommentService {
	String put(Comment model) throws IOException;
	List<Comment> list(String pageId) throws IOException;
	Comment get(String id);
	List<Comment> listSpamComments(String pageId) throws IOException;
	void delete(String id);
}
