package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.security.SecureRandom;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialFormModel;
import com.udacity.jwdnd.course1.cloudstorage.security.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.CloudStorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping(path = "/credentials")
public class CredentialsController {

	private SecureRandom secureRandom = new SecureRandom();

	private Logger logger = LoggerFactory.getLogger(CredentialsController.class);

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private CloudStorageService cloudStorageService;

	@Autowired
	private UserService userService;

	@PostMapping
	public String createCredential(@ModelAttribute CredentialFormModel credentialFormModel,
			HttpServletRequest request) {
		String userName = request.getUserPrincipal().getName();
		logger.info("Saving credential: " + credentialFormModel);
		Credential model = credentialFormModel.toModel();
		
		model.setUserId(userService.getUser(userName).getUserId());
		
		byte[] keyBytes = new byte[16];
		secureRandom.nextBytes(keyBytes);

		String key = Base64.getEncoder().encodeToString(keyBytes);
		model.setKey1(key);
		
		String originalPassword = model.getPassword();
		String encryptedPassword = encryptionService.encryptValue(originalPassword, key);
		
		model.setPassword(encryptedPassword);

		if (credentialFormModel.getCredentialId() == null) {	
			cloudStorageService.addCredential(model);
		} else {
			model.setCredentialId(credentialFormModel.getCredentialId());
			cloudStorageService.updateCredential(model);
		}

		return "redirect:/home";
	}
	
	@GetMapping
	@RequestMapping(path = "/delete/{id}")
	public String delete(@PathVariable("id") String credentialId) {
		Integer credentialIdInt = Integer.parseInt(credentialId);
		cloudStorageService.deleteCredential(credentialIdInt);
		return "redirect:/home";
	}
}
