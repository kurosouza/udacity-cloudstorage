package com.udacity.jwdnd.course1.cloudstorage.models;

public class CredentialFormModel {

	private Integer credentialId;
	private String userName;
	private String password;
	private String url;
	private String key1;

	public CredentialFormModel(Integer credentialId, String userName, String password, String url, String key1) {
		super();
		this.credentialId = credentialId;
		this.userName = userName;
		this.password = password;
		this.url = url;
		this.key1 = key1;
	}
	
	public CredentialFormModel(String userName, String password, String url, String key1) {
		this(null, userName, password, url, key1);
	}
	
	public CredentialFormModel() {}

	public Integer getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Integer credentialId) {
		this.credentialId = credentialId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}
	
	public Credential toModel() {
		Credential model = new Credential(credentialId, userName,password, key1, url, null);
		return model;
	}

	@Override
	public String toString() {
		return "CredentialFormModel [credentialId=" + credentialId + ", userName=" + userName + ", password=" + password
				+ ", url=" + url + ", key1=" + key1 + "]";
	}

	
}
