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
import com.strikers.elitematrimony.service.ProfileMappingService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileMappingControllerTest {

	@InjectMocks
	ProfileMappingController profileMappingController;

	@Mock
	ProfileMappingService profileMappingService;
	
	static Profile profile = new Profile();
	static List<Profile> profilelst = new ArrayList<>();

	@Test
	public void getInterestedProfilesPositive() {
		profile.setProfileId(1);
		profilelst.add(profile);
		Mockito.when(profileMappingService.getInterestedProfiles(1)).thenReturn(profilelst);
		HttpStatus statuscode = profileMappingController.getInterestedProfiles(1).getStatusCode();
		assertEquals(HttpStatus.OK, statuscode);
	}
	
	@Test
	public void getInterestedProfilesNegativeEmpty() {
		profile.setProfileId(2);
		List<Profile> profilelsts = null;
		Mockito.when(profileMappingService.getInterestedProfiles(1)).thenReturn(profilelsts);
		HttpStatus statuscode = profileMappingController.getInterestedProfiles(1).getStatusCode();
		assertEquals(HttpStatus.NO_CONTENT, statuscode);
	}
	
}
