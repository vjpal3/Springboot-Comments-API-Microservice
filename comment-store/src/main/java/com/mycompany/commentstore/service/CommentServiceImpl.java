package com.mycompany.commentstore.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.commentsapi.spamdetection.SpamDetector;

@Service
public class CommentServiceImpl {

	@Autowired 
	private SpamDetector spamDetector;
	
	@Autowired
	private CommentRepository repository;
}
