package com.strikers.elitematrimony.controller;

import java.util.List;

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

import lombok.extern.slf4j.Slf4j;

/**
 * LanguageController is the controller used to show the list of languages
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/languages")
@Slf4j
public class LanguageController {
	
	@Autowired
	LanguageService languageService;
	
	/**
	 * @author Hema
	 * listLanguage is used to list the languages
	 * @return
	 * @throws LanguageNotFoundException
	 */
	@GetMapping()
	public ResponseEntity<List<Language>> listLanguage() throws LanguageNotFoundException {
		log.info("Listing all the Languages");
		List<Language> languageList = languageService.listLanguage();
		if (languageList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(languageList, HttpStatus.OK);
		}
	}

}
