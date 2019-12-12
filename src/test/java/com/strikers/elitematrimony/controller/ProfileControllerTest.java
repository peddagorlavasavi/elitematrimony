package com.strikers.elitematrimony.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.service.ProfileServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProfileControllerTest {

	@InjectMocks
	ProfileController profileController;

	@Mock
	ProfileServiceImpl profileService;

	Profile profile = new Profile();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		profile.setProfession("mca");

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
