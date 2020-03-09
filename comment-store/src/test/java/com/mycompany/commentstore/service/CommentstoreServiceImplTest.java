package com.mycompany.commentstore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.model.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentstoreServiceImplTest {
	@Autowired 
	private CommentRepository repository;
	
	@Autowired 
	private CommentService service;
	
	private Comment model;
	
	@Before 
	public void setup() {
		model = new Comment();
		model.setUsernme("testuser");
		model.setId("dqe345e456rf34rw");
		model.setPageId("product0815");
		model.setEmailAddress("example@example.com");
		model.setComment("I am the comment");
		repository.deleteAll();
	}
	
	@Test
	public void testList() throws IOException {
		service.put(model);
		List<Comment> r = service.list(model.getPageId());
		assertNotNull(r);
		assertEquals(1, r.size());
		assertEquals(model.getId(), r.get(0).getId());
	}
	
	@Test
	public void testListNotFound() throws IOException {
		service.put(model);
		List<Comment> r = service.list("notfound");
		assertTrue(r.isEmpty());
	}
}
