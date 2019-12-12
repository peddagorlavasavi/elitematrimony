package com.strikers.elitematrimony.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.repository.ProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceImplTest {
	@InjectMocks
	ProfileServiceImpl profileService;

	@Mock
	ProfileRepository profileRepository;
	Profile profile = new Profile();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		profile.setProfession("mca");

	}

	@Test
	public void testSearchProfile() {
		List<Profile> profileList = new ArrayList<>();
		when(profileRepository.searchProfile("mca")).thenReturn(profileList);
		List<Profile> profile = profileService.searchProfile("mca");
		assertNotNull(profile);

	}
}
