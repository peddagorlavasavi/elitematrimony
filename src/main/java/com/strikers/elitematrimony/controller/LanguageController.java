package com.strikers.elitematrimony.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.exception.LanguageNotFoundException;
import com.strikers.elitematrimony.service.LanguageService;

/**
 * LanguageController is the controller used to show the list of languages
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/languages")
public class LanguageController {
	
	@Autowired
	LanguageService languageService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	/**
	 * @author Hema
	 * @description -> this method is used to list the languages
	 * @return List<Language>
	 * @throws LanguageNotFoundException
	 */
	@GetMapping()
	public ResponseEntity<List<Language>> listLanguage() throws LanguageNotFoundException {
		logger.info("Listing all the Languages");
		List<Language> languageList = languageService.listLanguage();
		if (languageList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(languageList, HttpStatus.OK);
		}
	}

}
