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
import org.springframework.beans.BeanUtils;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.utils.StringConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileServiceTest {

	@InjectMocks
	ProfileServiceImpl profileServiceImpl;

	@Mock
	ProfileRepository profileRepository;

	static Profile profile = new Profile();
	static LoginRequestDto loginRequestDto = new LoginRequestDto();
	static LoginResponseDto loginResponseDto = new LoginResponseDto();
	static SuggestedListRequestDto suggestedListRequestDto = new SuggestedListRequestDto();
	static SuggestedListResponseDto suggestedListResponseDto = new SuggestedListResponseDto();
	static List<Profile> profiles = new ArrayList<>();
	static List<SuggestedListResponseDto> suggestedListResponseDtos = new ArrayList<>();

	@Before
	public void setUp() {
		profile.setProfileId(1);
		profile.setMobileNumber("9894803625");
		profile.setPassword("12");
		profiles.add(profile);
		loginRequestDto.setMobileNumber("9894803625");
		loginRequestDto.setPassword("12");
		loginResponseDto.setMessage(StringConstant.LOGIN_SUCCESS);
		loginResponseDto.setFirstName("Hema");
		loginResponseDto.setGender("Female");
		loginResponseDto.setProfileId(1);
		suggestedListRequestDto.setProfileId(1);
		suggestedListRequestDto.setGender("Female");
		BeanUtils.copyProperties(profiles, suggestedListResponseDtos);
	}

	@Test
	public void testUserLoginPositive() throws ProfileNotFoundException {
		Mockito.when(profileRepository.findByMobileNumberAndPassword("9894803625", "12")).thenReturn(profile);
		LoginResponseDto loginResponseDtos = profileServiceImpl.userLogin(loginRequestDto);
		assertEquals(StringConstant.LOGIN_SUCCESS, loginResponseDtos.getMessage());
	}

	@Test(expected = ProfileNotFoundException.class)
	public void testUserLoginNegative() throws ProfileNotFoundException {
		Mockito.when(profileRepository.findByMobileNumberAndPassword("9894803625", "1")).thenReturn(profile);
		LoginResponseDto loginResponseDtos=profileServiceImpl.userLogin(loginRequestDto);
		assertEquals(null, loginResponseDtos);
	}


}
