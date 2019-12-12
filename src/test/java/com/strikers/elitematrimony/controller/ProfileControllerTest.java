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
import org.springframework.beans.BeanUtils;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;
import com.strikers.elitematrimony.service.ProfileService;
import com.strikers.elitematrimony.util.StringConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileControllerTest {

	@InjectMocks
	ProfileController profileController;

	@Mock
	ProfileService profileService;

	static LoginResponseDto loginResponseDto = new LoginResponseDto();
	static LoginRequestDto loginRequestDto = new LoginRequestDto();
	static Profile profile = new Profile();
	static List<Profile> profiles = new ArrayList<>();
	static SuggestedListRequestDto suggestedListRequestDto = new SuggestedListRequestDto();
	static List<SuggestedListResponseDto> suggestedListResponseDtos = new ArrayList<>();

	@Before
	public void setUp() {
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
	public void testUserLoginPositive() throws ProfileNotFoundException {
		Mockito.when(profileService.userLogin(loginRequestDto)).thenReturn(loginResponseDto);
		Integer result = profileController.userLogin(loginRequestDto).getStatusCodeValue();
		assertEquals(200, result);
	}

	/*
	 * @Test(expected = ProfileNotFoundException.class) public void
	 * testUserLoginNegative() throws ProfileNotFoundException { LoginRequestDto
	 * loginRequestDtos = new LoginRequestDto(); LoginResponseDto loginResponseDtos
	 * = new LoginResponseDto();
	 * Mockito.when(profileService.userLogin(loginRequestDtos)).thenReturn(
	 * loginResponseDtos); Integer result =
	 * profileController.userLogin(loginRequestDto).getStatusCodeValue();
	 * assertEquals(204, result); }
	 */

	/*
	 * @Test public void testListProfilePositive() throws ProfileNotFoundException {
	 * Mockito.when(profileService.suggestedList(suggestedListRequestDto)).
	 * thenReturn(suggestedListResponseDtos); Integer result =
	 * profileController.listProfile(suggestedListRequestDto).getStatusCodeValue();
	 * assertEquals(200, result); }
	 */
}
