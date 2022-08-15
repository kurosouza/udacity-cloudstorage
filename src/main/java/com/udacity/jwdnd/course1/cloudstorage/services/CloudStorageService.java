package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;

@Service
public class CloudStorageService {
	
	private Logger logger = LoggerFactory.getLogger(CloudStorageService.class);

	private FileMapper fileMapper;
	private NoteMapper noteMapper;
	private CredentialMapper credentialMapper;
	
	public CloudStorageService(FileMapper fileMapper, NoteMapper noteMapper, CredentialMapper credentialMapper) {
		this.fileMapper = fileMapper;
		this.noteMapper = noteMapper;
		this.credentialMapper  = credentialMapper;
	}
	
	// Files
	
	public boolean addFile(FileModel fileModel) {
		fileMapper.insert(fileModel);
		return true;
	}
	
	public List<FileModel> getAllFiles(Integer userId) {
		return fileMapper.getUserFiles(userId);
	} 
	
	public int deleteFile(Integer fileId) {
		return fileMapper.delete(fileId);
	}
	
	public FileModel getFileById(Integer id) {
		return fileMapper.getFile(id);
	}
	
	// Notes
	
	public boolean addNote(Note note) {
		int created = noteMapper.createNote(note);
		return created > 0;
	}
	
	public List<Note> getAllNotes(Integer userId) {
		return noteMapper.getUserNotes(userId);
	}
	
	public Integer updateNote(Note note) {
		logger.info("Updating Note: " + note);
		return noteMapper.updateNote(note);
	}
	
	public void deleteNote(Integer noteId) {
		noteMapper.deleteNote(noteId);	
	}
	
	// Credentials
	
	public int addCredential(Credential credential) {
		logger.info("Adding credential: " + credential);
		int result = credentialMapper.insert(credential);
		return result;
	}
	
	public List<Credential> getUserCredentials(Integer userId) {
		List<Credential> userCredentials = credentialMapper.getCredentialsByUserId(userId);
		return userCredentials;
	}
	
	public int updateCredential(Credential credential) {
		logger.info("Updating credential: " + credential);
		return credentialMapper.update(credential);
	}
	
	public int deleteCredential(Integer credentialId) {
		return credentialMapper.delete(credentialId);
	}	
	
}
