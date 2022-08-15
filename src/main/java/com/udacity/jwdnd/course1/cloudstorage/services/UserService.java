
package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.security.HashService;

@Service
public class UserService {
	
	private final UserMapper userMapper;
	private final HashService hashService;
	
	public UserService(UserMapper userMapper, HashService hashService) {
		super();
		this.userMapper = userMapper;
		this.hashService = hashService;
	}

	public int createUser(User user) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
		return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
	}
	
	public User getUser(String userName) {
		return userMapper.getUser(userName);
	}
	
	public boolean userExists(String userName) {
		return userMapper.getUser(userName) != null;
	}
}
