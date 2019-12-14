package com.strikers.elitematrimony.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.strikers.elitematrimony.entity.ProfileMapping;
import com.strikers.elitematrimony.repository.ProfileMappingRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.utils.ProfileComposer;
import com.strikers.elitematrimony.utils.StringConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProfilMappingeServiceImplTest {
	
	@InjectMocks
	ProfileServiceImpl profileService;

	@Mock
	ProfileRepository profileRepository;
	
	@Mock
	ProfileMappingRepository profileMappingRepository;
	
	@Mock
	ProfileComposer<List<Object[]>, List<Profile>> profileComposer;

	
	Profile profile = new Profile();

	static ProfileMapping profileMapping=new ProfileMapping();

	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		profile.setProfileId(123);
		profile.setProfession("mca");
		profile.setAddress("abc");
		profile.setAge(28);
		profile.setCity("banglore");
		profile.setDescription("abc");
		profile.setDob(LocalDate.of(1992, 10, 10));
		profile.setEmail("abc@gmail.com");
		profile.setFirstName("sri");
		profile.setGender("female");
		profile.setHobby("jogging");
		profile.setLanguage("tamil");
		profile.setLastName("keerthna");
		profile.setMaritalStatus("single");
		profile.setMobileNumber("782378237");
		profile.setMonthlyIncome(200000D);
		profile.setPassword("abcdef");
		profile.setProfession("IT");
		profile.setQualification("BE");
		profile.setUserName("sri");
		profile.setStatus(StringConstant.ACCEPTED_STATUS);
	}

	@Test
	public void testGetInterestedProfilesPositive() {
		Integer profileId=123;
		List<Object[]> objects = new ArrayList<Object[]>();
		List<Profile> profiles = new ArrayList<Profile>();
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(profile);
		
		objects=profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.INTERESTED_STATUS);
		
		when(profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.INTERESTED_STATUS)).thenReturn(objects);
		
		profiles = profileComposer.compose(objects);

		when(profileComposer.compose(objects)).thenReturn(profiles);

		assertNotNull(profile);
		assertNotNull(objects);
		assertNull(profiles);
	}
	

	@Test
	public void testGetInterestedProfilesNegative() {
		Integer profileId=null;
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(null);
		profile=profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS);
		assertNull(profile);

	}
	
	@Test
	public void testGetInterestedProfilesPositiveNegative() {
		Integer profileId=123;
		List<Object[]> objects =null;
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(profile);
		profile=profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS);
		
		when(profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.INTERESTED_STATUS)).thenReturn(objects);
		objects=profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.INTERESTED_STATUS);
		
		assertNotNull(profile);
		assertNull(objects);

	}
	
	@Test
	public void testGetMyInterestedProfilesPositive() {
		Integer profileId=123;
		List<Object[]> objects = new ArrayList<Object[]>();
		List<Profile> profiles = new ArrayList<Profile>();
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(profile);
		
		objects=profileMappingRepository.getMyInterestProfiles(profileId,
				StringConstant.INTERESTED_STATUS);
		
		when(profileMappingRepository.getMyInterestProfiles(profileId,
				StringConstant.INTERESTED_STATUS)).thenReturn(objects);
		
		profiles = profileComposer.compose(objects);

		when(profileComposer.compose(objects)).thenReturn(profiles);

		assertNotNull(profile);
		assertNotNull(objects);
		assertNull(profiles);
	}
	

	@Test
	public void testGetMyInterestedProfilesNegative() {
		Integer profileId=null;
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(null);
		profile=profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS);
		assertNull(profile);

	}
	
	@Test
	public void testGetMyInterestedProfilesPositiveNegative() {
		Integer profileId=123;
		List<Object[]> objects =null;
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(profile);
		profile=profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS);
		
		when(profileMappingRepository.getMyInterestProfiles(profileId,
				StringConstant.INTERESTED_STATUS)).thenReturn(objects);
		objects=profileMappingRepository.getMyInterestProfiles(profileId,
				StringConstant.INTERESTED_STATUS);
		
		assertNotNull(profile);
		assertNull(objects);

	}
	
	@Test
	public void testGetAcceptedProfilesPositive() {
		Integer profileId=123;
		List<Object[]> objects = new ArrayList<Object[]>();
		List<Profile> profiles = new ArrayList<Profile>();
		List<Profile> profileList = new ArrayList<Profile>();
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(profile);
		
		objects=profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.INTERESTED_STATUS);
		
		when(profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.INTERESTED_STATUS)).thenReturn(objects);
		
		List<Object[]> objectList = profileMappingRepository.getMyInterestProfiles(profileId,
				StringConstant.ACCEPTED_STATUS);
		
		when(profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.ACCEPTED_STATUS)).thenReturn(objects);
		
		profiles = profileComposer.compose(objects);
		
		profileList = profileComposer.compose(objectList);


		when(profileComposer.compose(objects)).thenReturn(profiles);

		assertNotNull(profile);
		assertNotNull(objects);
		assertNull(profiles);
		assertNull(profileList);
	}
	

	@Test
	public void testGetAcceptedProfilesNegative() {
		Integer profileId=null;
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(null);
		profile=profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS);
		assertNull(profile);

	}
	
	@Test
	public void testGetAcceptedProfilesPositiveNegative() {
		Integer profileId=123;
		List<Object[]> objects =null;
		when(profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS)).thenReturn(profile);
		profile=profileRepository.findByProfileId(profileId,StringConstant.ACTIVE_STATUS);
		
		when(profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.ACCEPTED_STATUS)).thenReturn(objects);
		objects=profileMappingRepository.getInterestedProfiles(profileId,
				StringConstant.ACCEPTED_STATUS);
		
		assertNotNull(profile);
		assertNull(objects);

	}
}
