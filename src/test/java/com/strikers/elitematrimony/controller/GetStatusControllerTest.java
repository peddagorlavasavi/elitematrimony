package com.strikers.elitematrimony.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.entity.ProfileMapping;
import com.strikers.elitematrimony.service.ProfileMappingService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetStatusControllerTest {

	@InjectMocks
	ProfileMappingController profileMappingController;

	@Mock
	ProfileMappingService profileMappingService;

	static ProfileMapping profileMapping = new ProfileMapping();
	static List<ProfileMapping> profileMappinglst = new ArrayList<>();
	static Profile profile = new Profile();
	static List<Profile> profilelst = new ArrayList<>();

	@Test
	public void getMyInterestProfilesPositive() {
		profile.setProfileId(1);
		profilelst.add(profile);
		Mockito.when(profileMappingService.getMyInterestProfiles(1)).thenReturn(profilelst);
		HttpStatus statuscode = profileMappingController.getMyInterestProfiles(1).getStatusCode();
		assertEquals(HttpStatus.OK, statuscode);
	}

	@Test
	public void getMyInterestProfilesNegative() {
		profile.setProfileId(2);
		profilelst.add(profile);
		Mockito.when(profileMappingService.getMyInterestProfiles(1)).thenReturn(profilelst);
		HttpStatus statuscode = profileMappingController.getMyInterestProfiles(2).getStatusCode();
		assertEquals(HttpStatus.OK, statuscode);
	}

	@Test
	public void getMyMatchingProfilesPositive() {
		profile.setProfileId(1);
		profilelst.add(profile);
		Mockito.when(profileMappingService.getAcceptedProfiles(1)).thenReturn(profilelst);
		HttpStatus statuscode = profileMappingController.getAcceptedProfiles(1).getStatusCode();
		assertEquals(HttpStatus.OK, statuscode);
	}

	@Test
	public void getMyMatchingProfilesNegative() {
		profile.setProfileId(2);
		profilelst.add(profile);
		Mockito.when(profileMappingService.getAcceptedProfiles(1)).thenReturn(profilelst);
		HttpStatus statuscode = profileMappingController.getAcceptedProfiles(2).getStatusCode();
		assertEquals(HttpStatus.OK, statuscode);
	}
}
