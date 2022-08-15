package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {

	private Integer credentialId;
	private String username;
	private String key1;
	private String password;
	private String url;
	private Integer userId;
	
	public Credential() { }

	public Credential(Integer id, String username, String password, String key1, String url, Integer userId) {
		super();
		this.credentialId = id;
		this.username = username;
		this.key1 = key1;
		this.password = password;
		this.url = url;
		this.userId = userId;
	}
	
	public Credential(String username, String password, String url, String key1, Integer userId) {
		this(null, username, password, key1, url, userId);
	}

	public Integer getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Integer id) {
		this.credentialId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	@Override
	public String toString() {
		return "Credential [credentialId=" + credentialId + ", username=" + username + ", key1=" + key1 + ", password="
				+ password + ", url=" + url + ", userId=" + userId + "]";
	}

	public CredentialFormModel toFormModel() {
		CredentialFormModel model = new CredentialFormModel(username, password, url, key1);
		return model;
	}
	
	
}
