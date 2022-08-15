package com.udacity.jwdnd.course1.cloudstorage.models;

public class NoteFormModel {

	private Integer noteId;
	private String title;
	private String description;
	
	public NoteFormModel() {}
	
	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer id) {
		this.noteId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "title = " + this.title + ", description = " + this.description + " ";
	}
	
	public Note toModel() {	
		Note note = new Note();
		note.setTitle(this.getTitle());
		note.setDescription(this.getDescription());
		note.setNoteId(this.noteId);
		
		return note;
	}
	
}
