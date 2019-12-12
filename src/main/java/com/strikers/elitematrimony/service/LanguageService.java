package com.strikers.elitematrimony.service;

import java.util.List;

import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.exception.LanguageNotFoundException;

public interface LanguageService {
	
	public List<Language> listLanguage() throws LanguageNotFoundException;

}
