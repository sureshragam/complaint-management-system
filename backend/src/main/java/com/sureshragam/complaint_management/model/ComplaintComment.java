package com.sureshragam.complaint_management.model;

import com.sureshragam.complaint_management.enums.AuthorType;
import java.time.LocalDateTime;

public class ComplaintComment {
	private Long id;
	private Long complaint_id;
	private AuthorType author_type;
	private Long author_id;
	private String comment_text;
	private LocalDateTime created_at;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(Long complaint_id) {
		this.complaint_id = complaint_id;
	}
	public AuthorType getAuthor_type() {
		return author_type;
	}
	public void setAuthor_type(AuthorType author_type) {
		this.author_type = author_type;
	}
	public Long getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	
}
