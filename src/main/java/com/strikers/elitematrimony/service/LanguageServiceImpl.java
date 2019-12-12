package com.strikers.elitematrimony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.exception.LanguageNotFoundException;
import com.strikers.elitematrimony.repository.LanguageRepository;
import com.strikers.elitematrimony.util.StringConstant;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageRepository languageRepository;

	/**
	 * @author Hema
	 * listLanguage is used to list the languages
	 * @return
	 * @throws LanguageNotFoundException
	 */
	@Override
	public List<Language> listLanguage() throws LanguageNotFoundException {
		List<Language> languageList = languageRepository.findAll();
		if (languageList.isEmpty()) {
			throw new LanguageNotFoundException(StringConstant.LANGUAGE_NOT_FOUND);
		}
		return languageList;

	}

}
