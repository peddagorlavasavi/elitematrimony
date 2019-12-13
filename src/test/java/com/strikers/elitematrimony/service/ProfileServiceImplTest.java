package com.strikers.elitematrimony.service;

import static org.junit.Assert.assertNotNull;
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

import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.repository.CityRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceImplTest {
	@InjectMocks
	ProfileServiceImpl profileService;

	@Mock
	ProfileRepository profileRepository;
	
	@Mock
	CityRepository cityRepository;
	
	Profile profile = new Profile();

	static ProfileRequestDto profileRequestDto=new ProfileRequestDto();
	static City city=new City();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		profile.setProfession("mca");
		profileRequestDto.setAddress("abc");
		profileRequestDto.setAge(28);
		profileRequestDto.setCity("banglore");
		profileRequestDto.setDescription("abc");
		profileRequestDto.setDob(LocalDate.of(1992, 10, 10));
		profileRequestDto.setEmail("abc@gmail.com");
		profileRequestDto.setFirstName("sri");
		profileRequestDto.setGender("female");
		profileRequestDto.setHobby("jogging");
		profileRequestDto.setLanguage("tamil");
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
	}

	@Test
	public void testSearchProfile() {
		List<Profile> profileList = new ArrayList<>();
		when(profileRepository.searchProfile("mca","")).thenReturn(profileList);
		List<Profile> profile = profileService.searchProfile("mca","");
		assertNotNull(profile);

	}
	
	@Test
	public void testCreateProfilePositive() {
		Profile profile = new Profile();
		BeanUtils.copyProperties(profileRequestDto, profile);
		profile.setCreatedDate(LocalDate.of(2019, 11, 12));
		profile.setStatus(com.strikers.elitematrimony.utils.StringConstant.ACTIVE_STATUS);
		profile.setUserName(profileRequestDto.getMobileNumber());
		profile.setAge(Utils.calculateAge(profileRequestDto.getDob()));
		when(cityRepository.findById(1)).thenReturn(Optional.of(city));
		profile.setCity(Optional.of(city).get().getCityName());
	}
}
