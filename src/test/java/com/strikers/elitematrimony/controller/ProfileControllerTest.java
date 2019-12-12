package com.strikers.elitematrimony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.service.ProfileServiceImpl;

/**
 * @author vasavi
 * @since 2019-12-12
 * @description -> this class is used for to do test operation for profile controller.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileControllerTest {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProfileControllerTest.class);
	/**
	 * The profileController
	 */
	@InjectMocks
	ProfileController profileController;

	/**
	 * The profileService
	 */
	@Mock
	ProfileServiceImpl profileService;

	Profile profile = new Profile();
	ProfileRequestDto profileRequestDto = new ProfileRequestDto();
	ProfileResponseDto profileResponseDto = new ProfileResponseDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		profileRequestDto.setAddress("bangalore");
		profileRequestDto.setAge(25);
		profileRequestDto.setCity("bangalore");
		profileRequestDto.setCreatedDate(LocalDate.of(2019, 12, 12));
		profileRequestDto.setDescription("abc");
		profileRequestDto.setDob(LocalDate.of(2000, 12, 12));
		profileRequestDto.setEmail("vasavi@gmail.com");
		profileRequestDto.setFirstName("vasavi");
		profileRequestDto.setGender("female");
		profileRequestDto.setHobby("reading books");
		profileRequestDto.setLanguage("telugu");
		profileRequestDto.setLastName("p");
		profileRequestDto.setMaritalStatus("unmarried");
		profileRequestDto.setMobileNumber("9538156731");
		profileRequestDto.setPassword("vasavi@123");
		profileRequestDto.setMonthlyIncome(10000.00);
		profileRequestDto.setQualification("mca");
		profileRequestDto.setStatus("active");
		profileRequestDto.setUserName("vasavi");
		profileResponseDto.setMessage("profile created successfully");
		profileResponseDto.setProfileId(1);
		profileResponseDto.setStatusCode(200);

	}

	@Test
	public void testCreateProfile() throws AgeNotMatchedException {
		logger.info("Inside the creteProfileTest method");
		when(profileService.createProfile(profileRequestDto)).thenReturn(profileResponseDto);
		ResponseEntity<ProfileResponseDto> result = profileController.createProfile(profileRequestDto);
		assertEquals("profile created successfully", result.getBody().getMessage());
	}
}
