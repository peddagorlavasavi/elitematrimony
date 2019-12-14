package com.strikers.elitematrimony.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.ArgumentMatchers.any;
import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.repository.CityRepository;
import com.strikers.elitematrimony.repository.LanguageRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceImplTest {
	@InjectMocks
	ProfileServiceImpl profileService;

	@Mock
	ProfileRepository profileRepository;

	@Mock
	CityRepository cityRepository;
	@Mock
	LanguageRepository languageRepository;

	Profile profile = new Profile();

	static ProfileRequestDto profileRequestDto = new ProfileRequestDto();
	static ProfileResponseDto profileResponsetDto = new ProfileResponseDto();
	static SuggestedListResponseDto suggestedListResponseDto = new SuggestedListResponseDto();
	static SuggestedListRequestDto suggestedListRequestDto = new SuggestedListRequestDto();
	static City city = new City();
	static Language language = new Language();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		profile.setProfession("mca");
		profileRequestDto.setAddress("abc");
		profileRequestDto.setAge(28);
		profileRequestDto.setCity("1");
		profileRequestDto.setDescription("abc");
		profileRequestDto.setDob(LocalDate.of(1992, 10, 10));
		profileRequestDto.setEmail("abc@gmail.com");
		profileRequestDto.setFirstName("sri");
		profileRequestDto.setGender("female");
		profileRequestDto.setHobby("jogging");
		profileRequestDto.setLanguage("1");
		profileRequestDto.setLastName("keerthna");
		profileRequestDto.setMaritalStatus("single");
		profileRequestDto.setMobileNumber("782378237");
		profileRequestDto.setMonthlyIncome(200000D);
		profileRequestDto.setPassword("abcdef");
		profileRequestDto.setProfession("IT");
		profileRequestDto.setQualification("BE");
		profileRequestDto.setUserName("sri");
		city.setCityId(1);
		city.setCityName(profileRequestDto.getCity());
		language.setLanguageId(1);
		language.setLanguageName(profileRequestDto.getLanguage());
	}

	@Test
	public void testSearchProfile() {
		List<Profile> profileList = new ArrayList<>();
		when(profileRepository.searchProfile("mca", "")).thenReturn(profileList);
		List<Profile> profile = profileService.searchProfile("mca", "");
		assertNotNull(profile);

	}

	@Test
	public void testCreateProfile() throws AgeNotMatchedException {
		Profile profile = new Profile();
		BeanUtils.copyProperties(profileRequestDto, profile);
		profile.setCreatedDate(LocalDate.of(2019, 11, 12));
		profile.setStatus(com.strikers.elitematrimony.utils.StringConstant.ACTIVE_STATUS);
		profile.setUserName(profileRequestDto.getMobileNumber());
		profile.setAge(20);
		when(cityRepository.findById(any())).thenReturn(Optional.of(city));
		when(languageRepository.findById(any())).thenReturn(Optional.of(language));
		when(profileRepository.save(any())).thenReturn(profile);
		ProfileResponseDto profileResponseDto = profileService.createProfile(profileRequestDto);
		assertEquals(200, profileResponseDto.getStatusCode());
	}

	@Test
	public void testCreateProfileNegative() throws AgeNotMatchedException {
		ProfileResponseDto profileResponseDto = profileService.createProfile(null);
		assertEquals(200, profileResponseDto.getStatusCode());
	}

	@Test(expected = AgeNotMatchedException.class)
	public void testCreateProfileAgefailure() throws AgeNotMatchedException {
		Profile profile = new Profile();
		BeanUtils.copyProperties(profileRequestDto, profile);
		profileRequestDto.setDob(LocalDate.of(2010, 10, 10));
		profile.setStatus(com.strikers.elitematrimony.utils.StringConstant.ACTIVE_STATUS);
		profile.setUserName(profileRequestDto.getMobileNumber());
		profile.setDob(LocalDate.of(2010, 12, 12));
		profile.setAge(8);
		when(cityRepository.findById(any())).thenReturn(Optional.of(city));
		when(languageRepository.findById(any())).thenReturn(Optional.of(language));
		when(profileRepository.save(any())).thenReturn(profile);

	}

	

}
