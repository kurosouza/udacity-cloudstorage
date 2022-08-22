package com.udacity.jwdnd.course1.cloudstorage.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteFormModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CloudStorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping(path = "/notes")
public class NotesController {

	private Logger logger = LoggerFactory.getLogger(NotesController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CloudStorageService cloudStorageService;

	@PostMapping
	public String create(@ModelAttribute("noteFormModel") NoteFormModel noteFormModel, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		logger.info("Received notes: " + noteFormModel);

		String userName = request.getUserPrincipal().getName();

		Integer userId = userService.getUser(userName).getUserId();

		Note note = noteFormModel.toModel();
		
		if (note.getNoteId() == null) {
			note.setUserId(userId);
			cloudStorageService.addNote(note);
			redirectAttributes.addFlashAttribute("notesSuccessMsg","Note added successfully.");
		} else {
			cloudStorageService.updateNote(note);
			redirectAttributes.addFlashAttribute("notesSuccessMsg","Note updated successfully.");
		}
		
		return "redirect:/home";
	}
	
	@GetMapping
	@RequestMapping(path = "/delete/{id}")
	public String delete(@PathVariable("id") String noteId, RedirectAttributes redirectAttributes) {
		cloudStorageService.deleteNote(Integer.parseInt(noteId));
		redirectAttributes.addFlashAttribute("notesSuccessMsg","Note deleted successfully.");
		return "redirect:/home";
	}
	
}