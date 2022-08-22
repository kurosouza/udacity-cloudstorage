package com.udacity.jwdnd.course1.cloudstorage.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.models.UserFormModel;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping(path="/signup")
public class SignupController {
	private final Logger logger = LoggerFactory.getLogger(SignupController.class);
	
	private final UserService userService;
	
	public SignupController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String signUp(@ModelAttribute("newUserForm") UserFormModel newUserForm, Model model) {		
		return "signup";
	}
	
	@PostMapping
	public String createUser(@ModelAttribute UserFormModel newUserForm, Model model,
			RedirectAttributes redirectAttributes) {
		logger.info("Recieved input user model: " + newUserForm);
		
		String signupError = null;
		
		if(userService.userExists(newUserForm.getUserName())) {
			signupError = "User already exists";
		}
		
		if(signupError == null) {
			int rowsAdded = userService.createUser(newUserForm.toUserModel());
			
			if(rowsAdded < 0) {
				signupError = "There was an error signing you up. Please try again ..";
			}
		}
		
		model.addAttribute("newUserForm", new UserFormModel());
		
		if(signupError == null) {
			redirectAttributes.addFlashAttribute("signupSuccess", true);
			model.addAttribute("signupSuccess", true);
			return "redirect:/login";
			
		} else {
			model.addAttribute("signupError", signupError);
		}
		
		return "signup";
	}

}
