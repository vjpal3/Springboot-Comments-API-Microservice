package com.mycompany.commentstore.restapi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.commentstore.service.CommentService;
import com.mycompany.model.Comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ReadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReadController.class);
	
	@Autowired
	private CommentService service;
	
	@RequestMapping(value = "/comments/{id}")
	public List<Comment> getComments(@PathVariable(value = "id") String pageId) throws IOException {
		List<Comment> r = service.list(pageId);
		
		if(r.isEmpty()) {
			throw new FileNotFoundException("/list/" + pageId);
		}
		return r;
	}
	
	@RequestMapping(value = "/comments/{id}/spam") 
	public List<Comment> getSpamComments(@PathVariable(value = "id") String pageId) throws IOException {
		List<Comment> r = service.listSpamComments(pageId);
		return r;
	}
	
	@RequestMapping(value = "/comments/{id}/paging")
	public Page<Comment> getCommentsPaging(
			@PathVariable(value = "id") String pageId, 
			Pageable pageable
	) throws IOException {
		return service.list(pageId, pageable);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handle404(Exception ex, Locale locale) {
		LOGGER.debug("Resource not found {}", ex.getMessage());
	}
	
	
}
