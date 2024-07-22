package com.svastha.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class ImageModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private MultipartFile[] file;
	private Long userId;
	private Long projectId;
	private Long primaryKey;
	private String formName;

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

}
