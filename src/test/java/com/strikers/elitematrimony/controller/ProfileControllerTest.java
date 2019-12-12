package com.strikers.elitematrimony.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;
import com.strikers.elitematrimony.service.ProfileService;
import com.strikers.elitematrimony.util.StringConstant;

/**
 * @author vasavi
 * @since 2019-12-12
 * @description -> this class is used for to do test operation for profile
 *              controller.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileControllerTest {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProfileControllerTest.class);

	@InjectMocks
	ProfileController profileController;

	@Mock
	ProfileService profileService;

	static LoginResponseDto loginResponseDto = new LoginResponseDto();
	static LoginRequestDto loginRequestDto = new LoginRequestDto();
	static List<Profile> profiles = new ArrayList<>();
	static SuggestedListRequestDto suggestedListRequestDto = new SuggestedListRequestDto();
	static List<SuggestedListResponseDto> suggestedListResponseDtos = new ArrayList<>();

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
		loginRequestDto.setMobileNumber("9894803625");
		loginRequestDto.setPassword("12");
		loginResponseDto.setFirstName("hema");
		loginResponseDto.setGender("Female");
		loginResponseDto.setMessage(StringConstant.LOGIN_SUCCESS);
		loginResponseDto.setProfileId(1);
		profile.setProfileId(1);
		profile.setMobileNumber("9894803625");
		profile.setPassword("12");
		profiles.add(profile);
		loginRequestDto.setMobileNumber("9894803625");
		loginRequestDto.setPassword("12");
		loginResponseDto.setMessage(StringConstant.LOGIN_SUCCESS);
		loginResponseDto.setFirstName("hema");
		loginResponseDto.setGender("Female");
		loginResponseDto.setProfileId(1);
		suggestedListRequestDto.setProfileId(1);
		suggestedListRequestDto.setGender("Female");
		BeanUtils.copyProperties(profiles, suggestedListResponseDtos);

	}

	@Test
	public void testCreateProfile() throws AgeNotMatchedException {
		logger.info("Inside the creteProfileTest method");
		when(profileService.createProfile(profileRequestDto)).thenReturn(profileResponseDto);
		ResponseEntity<ProfileResponseDto> result = profileController.createProfile(profileRequestDto);
		assertEquals("profile created successfully", result.getBody().getMessage());

	}

	@Test
	public void testUserLoginPositive() throws ProfileNotFoundException {
		Mockito.when(profileService.userLogin(loginRequestDto)).thenReturn(loginResponseDto);
		Integer result = profileController.userLogin(loginRequestDto).getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void testSearchProfile() {
		List<Profile> profileList = new ArrayList<>();
		when(profileService.searchProfile("mca")).thenReturn(profileList);
		ResponseEntity<List<Profile>> result = profileController.searchProfile("mca");
		assertThat(result.getBody()).hasSize(0);
	}

	@Test
	public void testSearchProfileNegative() {
		List<Profile> profileList = new ArrayList<>();
		when(profileService.searchProfile("")).thenReturn(profileList);
		ResponseEntity<List<Profile>> result = profileController.searchProfile("");
		assertThat(result.getBody()).hasSize(0);
	}
}
