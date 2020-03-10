package com.mycompany.commentstore.restapi;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mycompany.commentsapi.spamdetection.SpamDetector;
import com.mycompany.commentstore.service.CommentService;
import com.mycompany.model.Comment;

@RunWith(SpringRunner.class)
@WebMvcTest(ReadController.class)
@AutoConfigureMockMvc
@MockBean({ SpamDetector.class})
public class ReadControllerTest {
	
	@MockBean 
	private CommentService commentService;
	
	@Autowired 
	private MockMvc mvc;
	
	private DateTimeFormatter DTF = DateTimeFormatter.ISO_INSTANT;

	@Test
	public void testGetComments() throws Exception {
		Comment model = setupDummyModel();
		List<Comment> mockReturn = new ArrayList<Comment>();
		mockReturn.add(model);
		
		when(this.commentService.list(anyString())).thenReturn(mockReturn);
		
		this.mvc.perform(get("/comments/" + model.getPageId()))
		.andExpect(status().is(200))
		.andExpect(jsonPath("$[0].id", is(model.getId())))
		.andExpect(
				jsonPath("$[0].creationDate", is(DTF.format(model.getCreationDate()))))
		.andExpect(jsonPath("$[0].usernme", is(model.getUsernme())))
		.andExpect(jsonPath("$[0].comment", is(model.getComment())))
		.andExpect(jsonPath("$[0].emailAddress", is(model.getEmailAddress())));
		
		verify(this.commentService, times(1)).list(anyString());
	}
	
	private Comment setupDummyModel() {
    	Comment model = new Comment();
		model.setUsernme("testuser");
		model.setId("dqe345e456rf34rw");
		model.setPageId("product0829");
		model.setEmailAddress("example@example.com");
		model.setComment("I am the comment");
		model.setCreationDate(Instant.now());
		return model;
    }
}
