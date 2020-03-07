package com.mycompany.model;
import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(
		name="comments_model",
		indexes = { 
				@Index (name="idx_pageId",
						columnList = "pageId"
				)
		}
)

public class Comment implements Serializable{
	@Id
	@Column(length = 36)
	private String id;
	
	@Version
	private Integer version;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	@Column(length = 32)
	private String pageId;
	
	@Column(length = 32)
	private String usernme;
	
	@Column(length = 32)
	private String emailAddress;
	
	@Column
	private boolean spam;
	
	private Instant lastModificationDate;
	
	private Instant creationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getUsernme() {
		return usernme;
	}

	public void setUsernme(String usernme) {
		this.usernme = usernme;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isSpam() {
		return spam;
	}

	public void setSpam(boolean spam) {
		this.spam = spam;
	}

	public Instant getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Instant lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
	
}
