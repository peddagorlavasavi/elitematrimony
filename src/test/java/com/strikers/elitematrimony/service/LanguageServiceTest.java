package com.strikers.elitematrimony.service;

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
import com.strikers.elitematrimony.repository.LanguageRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LanguageServiceTest {
	
	@InjectMocks
	LanguageServiceImpl languageServiceImpl;
	
	@Mock
	LanguageRepository languageRepository;
	
	static Language language = new Language();
	static List<Language> languageList = new ArrayList<>();
	
	@Before
	public void setUp() {
		language.setLanguageId(1);
		language.setLanguageName("Tamil");
		languageList.add(language);
	}
	
	@Test
	public void testListLanguagePositive() throws LanguageNotFoundException  {
		Mockito.when(languageRepository.findAll()).thenReturn(languageList);
		List<Language> languageList = languageServiceImpl.listLanguage();
		assertEquals(1, languageList.size());
	}
	
	@Test(expected = LanguageNotFoundException.class)
	public void testListLanguageNegative() throws LanguageNotFoundException  {
		List<Language> languageLists = new ArrayList<>();
		Mockito.when(languageRepository.findAll()).thenReturn(languageLists);
		List<Language> listLanguage= languageServiceImpl.listLanguage();
		assertEquals(0, listLanguage.size());
	}

}
