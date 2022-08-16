package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CloudStorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping(path = "/files")
public class FilesController {
	
	private Logger logger = LoggerFactory.getLogger(FilesController.class);
	
	@Autowired
	private CloudStorageService cloudStorageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model, HttpServletRequest request) throws IOException {
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		
		logger.debug("Username = " + userName);
		
		User user = userService.getUser(userName);
		
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String fileSize = String.valueOf(file.getSize());
		
		FileModel fileModel = new FileModel();
		fileModel.setContentType(contentType);
		fileModel.setFileName(fileName);
		fileModel.setFileSize(fileSize);
		fileModel.setFileData(file.getBytes());
		fileModel.setUserId(user.getUserId());
		
		cloudStorageService.addFile(fileModel);
		
		logger.info("Received uploaded file: " + fileModel);
		
		return "redirect:/home";
	}
	
	@GetMapping
	@RequestMapping(path = "/delete/{id}")
	public String delete(@PathVariable("id") String fileId) {
		Integer fileIdInt = Integer.parseInt(fileId);
		cloudStorageService.deleteFile(fileIdInt);
		
		return "redirect:/home";
	}
	
	
	@GetMapping
	@RequestMapping(path = "/download/{id}")
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") String fileId) {
		Integer fileIdInt = Integer.parseInt(fileId);
		FileModel fileModel = cloudStorageService.getFileById(fileIdInt);
		
		InputStream inStream = new ByteArrayInputStream(fileModel.getFileData());
		
		MediaType contentType;
		
		if(fileModel.getContentType().equals("image/png")) {
			contentType = MediaType.IMAGE_PNG;
		} else if(fileModel.getContentType().equals("image/jpg") | fileModel.getContentType().equals("image/jpeg")) {
			contentType = MediaType.IMAGE_JPEG;
		} else {
			contentType = MediaType.APPLICATION_OCTET_STREAM;
		}
		
		return ResponseEntity.ok().contentType(contentType).body(new InputStreamResource(inStream));
		
	}

	
}