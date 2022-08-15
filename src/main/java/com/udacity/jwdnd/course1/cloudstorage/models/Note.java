package com.udacity.jwdnd.course1.cloudstorage.models;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public class Note {

	private Integer noteId;
	private String noteTitle;
	private String noteDescription;
	private Integer userId;
	
	public Note() {}

	public Note(String title, String description, Integer userId) {
		this.noteTitle = title;
		this.noteDescription = description;
		this.userId = userId;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return noteTitle;
	}

	public void setTitle(String title) {
		this.noteTitle = title;
	}

	public String getDescription() {
		return noteDescription;
	}

	public void setDescription(String description) {
		this.noteDescription = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Note [id=" + noteId + ", title=" + noteTitle + ", description=" + noteDescription + ", userId=" + userId + "]";
	}
	
	
	
}
