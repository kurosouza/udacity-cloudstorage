package com.udacity.jwdnd.course1.cloudstorage.models;

public class FileModel {

	private Integer fileId;
	private String fileName;
	private String contentType;
	private String fileSize;
	private byte[] fileData;
	private Integer userId;

	public FileModel() {
		
	}

	public FileModel(String fileName, String contentType, String fileSize, Integer userId) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.userId = userId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "FileModel [fileId=" + fileId + ", fileName=" + fileName + ", contentType=" + contentType + ", fileSize="
				+ fileSize + ", userId=" + userId + ", data = " + fileData + "]";
	}

}
