package com.mycompany.commentstore.restapi;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mycompany.commentstore.service.CommentService;
import com.mycompany.model.Comment;

@Controller
public class WriteController {
	
	@Autowired
	private CommentService service;

	@RequestMapping( value = "/comments", method = RequestMethod.POST)
	@ResponseStatus( value = HttpStatus.CREATED)
	public @ResponseBody String create( @ModelAttribute Comment model) throws IOException {
		
		return service.put(model);
	}
	
	@RequestMapping( value = "/comment/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( value = HttpStatus.OK)
	public void delete(@PathVariable(value = "id") String id, 
			HttpServletResponse response) throws IOException {
		service.delete(id);
	}
}
