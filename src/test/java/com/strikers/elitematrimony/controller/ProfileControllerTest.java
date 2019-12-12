package com.strikers.elitematrimony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;
import com.strikers.elitematrimony.service.ProfileService;
import com.strikers.elitematrimony.util.StringConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileControllerTest {
	
	@InjectMocks
	ProfileController profileController;
	
	@Mock
	ProfileService 	profileService;
	
	static LoginResponseDto loginResponseDto=new LoginResponseDto();
	static LoginRequestDto loginRequestDto=new LoginRequestDto();
	
	@Before
	public void setUp() {
		loginRequestDto.setMobileNumber("9894803625");
		loginRequestDto.setPassword("12345");
		loginResponseDto.setFirstName("hema");
		loginResponseDto.setGender("Female");
		loginResponseDto.setMessage(StringConstant.LOGIN_SUCCESS);
		loginResponseDto.setProfileId(1);
	}
	
	@Test
	public void testUserLoginPositive() throws ProfileNotFoundException {
		Mockito.when(profileService.userLogin(loginRequestDto)).thenReturn(loginResponseDto);
		Integer result = profileController.userLogin(loginRequestDto).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test(expected = ProfileNotFoundException.class)
	public void testUserLoginNegative() throws ProfileNotFoundException {
		LoginResponseDto loginResponseDtos=new LoginResponseDto();
		Mockito.when(profileService.userLogin(loginRequestDto)).thenReturn(loginResponseDtos);
		Integer result = profileController.userLogin(loginRequestDto).getStatusCodeValue();
		assertEquals(204, result);
	}
}
