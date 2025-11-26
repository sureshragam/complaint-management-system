package com.sureshragam.complaint_management.model;
import java.time.LocalDateTime;

public class ComplaintImage {
	private Long id;
	private Long complaint_id;
	private Boolean uploaded_by_user;
	private Long uploaded_by;
	private String file_url;
	private LocalDateTime created_at;
	private String description;
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
	public Boolean getUploaded_by_user() {
		return uploaded_by_user;
	}
	public void setUploaded_by_user(Boolean uploaded_by_user) {
		this.uploaded_by_user = uploaded_by_user;
	}
	public Long getUploaded_by() {
		return uploaded_by;
	}
	public void setUploaded_by(Long uploaded_by) {
		this.uploaded_by = uploaded_by;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
