package com.mycompany.commentstore.service;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mycompany.commentsapi.spamdetection.SpamDetector;
import com.mycompany.model.Comment;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired 
	private SpamDetector spamDetector;
	
	@Autowired
	private CommentRepository repository;

	@Override
	@Transactional
	public String put(Comment model) throws IOException {

		if(StringUtils.isEmpty(model.getId())) {
			model.setId(UUID.randomUUID().toString());
		}
		if(spamDetector.containsSpam(model.getUsernme()) || spamDetector.containsSpam(model.getEmailAddress()) 
				|| spamDetector.containsSpam(model.getComment())) {
			model.setSpam(true);
		}
		
		final Comment dbModel = get(model.getId());
		if(dbModel != null) {
			dbModel.setUsernme(model.getUsernme());
			dbModel.setComment(model.getComment());
			dbModel.setLastModificationDate(Instant.now());
			repository.save(dbModel);
		}
		else {
			model.setCreationDate(Instant.now());
			model.setLastModificationDate(Instant.now());
			repository.save(model);
		}
		return model.getId();
	}

	@Override
	public List<Comment> list(String pageId) throws IOException {
		return repository.findByPageId(pageId);
	} 
	
	@Override
	public Comment get(String id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Comment> listSpamComments(String pageId) throws IOException {
		return repository.findByPageIdAndSpamIsTrue(pageId);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public Page<Comment> list(String pageId, Pageable pageable) throws IOException {
		return repository.findByPageId(pageId, pageable);
	}
}
