package com.strikers.elitematrimony.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.entity.ProfileMapping;
import com.strikers.elitematrimony.repository.ProfileMappingRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.util.StringConstant;

@Service
public class ProfileMappingServiceImpl implements ProfileMappingService {

	@Autowired
	ProfileMappingRepository profileMappingRepository;

	@Autowired
	ProfileRepository profileRepository;

	/**
	 * @author Sri Keerthna
	 * @since2019-12-12
	 * This method take profileId as input and if that profile has any request for an interest.
	 *  It will get a list of persons who are interested on that particular id.
	 * @param profileId
	 * @return list of persons who are interested on a particular id.
	 */
	@Override
	public List<ProfileMapping> showInterestedProfiles(Integer profileId) {
		List<ProfileMapping> emptyList=new ArrayList<>();
		Profile profile = profileRepository.findByProfileId(profileId);
	if (profile != null) {
			List<ProfileMapping> profileMappings=profileMappingRepository.getRequestedProfile(profileId);
			List<ProfileMapping> profileMappingList= profileMappings.stream()
			.filter(profileMappping ->profileMappping.getAcceptedStatus().equalsIgnoreCase(StringConstant.INTERESTED_STATUS))
			.collect(Collectors.toList());
		return profileMappingList;
		}else {
			return emptyList;
		}
	
	}

	/**
	 * @author Sri Keerthna
	 * @since2019-12-12
	 * This method take profileId as input and if this profile has accepted by other id then 
	 * it will get a list of persons who accepted that particular id.
	 * @param profileId
	 * @return get a list of persons who accepted that particular id.
	 */
	@Override
	public List<ProfileMapping> showAcceptedProfiles(Integer profileId) {
		List<ProfileMapping> emptyList=new ArrayList<>();
		Profile profile = profileRepository.findByProfileId(profileId);
	if (profile != null) {
			List<ProfileMapping> profileMappings=profileMappingRepository.getRequestedProfile(profileId);
			List<ProfileMapping> profileMappingList= profileMappings.stream()
			.filter(profileMappping ->profileMappping.getAcceptedStatus().equalsIgnoreCase(StringConstant.ACCEPTED_STATUS))
			.collect(Collectors.toList());
		return profileMappingList;
		}else {
			return emptyList;
		}
	}

}
