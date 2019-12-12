package com.strikers.elitematrimony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.exception.LanguageNotFoundException;
import com.strikers.elitematrimony.service.LanguageService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LanguageControllerTest {
	
	@InjectMocks
	LanguageController languageController;
	
	@Mock
	LanguageService languageService;
	
	static Language language = new Language();
	static List<Language> languageList = new ArrayList<>();
	
	@Before
	public void setUp() {
		language.setLanguageId(1);
		language.setLanguageName("Tamil");
		languageList.add(language);
	}
	
	@Test
	public void testListLanguagePositive() throws LanguageNotFoundException {
		Mockito.when(languageService.listLanguage()).thenReturn(languageList);
		Integer result = languageController.listLanguage().getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testListLanguageNegative() throws LanguageNotFoundException  {
		List<Language> languages = new ArrayList<>();
		Mockito.when(languageService.listLanguage()).thenReturn(languages);
		Integer result = languageController.listLanguage().getStatusCodeValue();
		assertEquals(204, result);
	}


}


