package com.strikers.elitematrimony.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.controller.ProfileController;
import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.exception.LanguageNotFoundException;
import com.strikers.elitematrimony.repository.LanguageRepository;
import com.strikers.elitematrimony.utils.StringConstant;
/**
 * LanguageServiceImpl is used to implement LanguageService 
 * @author Hema
 * This controller is used to list all the languages 
 */
@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageRepository languageRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	/**
	 * @author Hema
	 * @description -> this method is used to list the languages
	 * @return List<Language>
	 * @throws LanguageNotFoundException
	 */
	@Override
	public List<Language> listLanguage() throws LanguageNotFoundException {
		logger.info("Listing all the languages");
		List<Language> languageList = languageRepository.findAll();
		if (languageList.isEmpty()) {
			throw new LanguageNotFoundException(StringConstant.LANGUAGE_NOT_FOUND);
		}
		return languageList;

	}

}
