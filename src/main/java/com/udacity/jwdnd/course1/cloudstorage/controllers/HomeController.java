package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialFormModel;
import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteFormModel;
import com.udacity.jwdnd.course1.cloudstorage.security.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.CloudStorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping(path = "/home")
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private CloudStorageService cloudStorageService;
	
	@Autowired
	private EncryptionService encryptionService;
	
	@GetMapping
	public String home(@ModelAttribute NoteFormModel noteFormModel, @ModelAttribute CredentialFormModel credentialFormModel, Model model, HttpServletRequest request) {
		String userName = request.getUserPrincipal().getName();
		Integer userId = userService.getUser(userName).getUserId();
		
		List<Note> notes = cloudStorageService.getAllNotes(userId);
		List<FileModel> files = cloudStorageService.getAllFiles(userId);
		
		List<Credential> credentials = cloudStorageService.getUserCredentials(userId).stream().map(u -> {
			String decryptedPw = encryptionService.decryptValue(u.getPassword(), u.getKey1());
			u.setPlainPw(decryptedPw);
			return u;
		}).collect(Collectors.toList());
		
		logger.info("Notes = " + notes);
		
		model.addAttribute("files", files);
		model.addAttribute("notes", notes);
		model.addAttribute("credentials", credentials);
		
		return "home";
	}
		
}