package com.mycompany.commentstore.restapi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.commentstore.service.CommentService;
import com.mycompany.model.Comment;

@RestController
public class ReadController {

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
	
	
	
}
