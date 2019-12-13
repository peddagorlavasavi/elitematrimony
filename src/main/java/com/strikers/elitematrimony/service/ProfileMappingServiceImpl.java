package com.strikers.elitematrimony.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.utils.StringConstant;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.entity.ProfileMapping;
import com.strikers.elitematrimony.repository.ProfileMappingRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;

@Service
public class ProfileMappingServiceImpl implements ProfileMappingService {

	@Autowired
	ProfileMappingRepository profileMappingRepository;

	@Autowired
	ProfileRepository profileRepository;

	/**
	 * @description @author Sujal @since2019-12-12 This method take profileId as
	 *              input and if this profile is shown in interested by other
	 *              profile.
	 * @param profileId
	 * @return get a list of persons who are interested on the given profile.
	 */
	@Override
	public List<Profile> getInterestedProfiles(Integer profileId) {
		List<Profile> profiles = new ArrayList<>();
		Profile profile = profileRepository.findByProfileId(profileId);
		if (profile != null) {
			List<ProfileMapping> profileMappings = profile.getInterestedProfiles();

			profileMappings.forEach(profileMapping -> {
				profiles.add(profileRepository.findByProfileId(profileMapping.getRequestedProfile().getProfileId()));
			});

			// profiles = profileMappingRepository.getInterestedProfiles();
			return profiles;
		} else {
			return profiles;
		}
	}

	/**
	 * @author Sri Keerthna @since2019-12-12 This method take profileId as input and
	 *         if that profile has any request for an interest. It will get a list
	 *         of persons who are interested on that particular id.
	 * @param profileId
	 * @return list of persons who are interested on a particular id.
	 */
	@Override
	public List<Profile> getMyInterestProfiles(Integer profileId) {
		List<Profile> emptyList = new ArrayList<>();
		Profile profile = profileRepository.findByProfileId(profileId);
		if (profile != null) {
			List<Profile> profiles = profileMappingRepository.getMyInterestProfiles(profileId,
					StringConstant.INTERESTED_STATUS);
			if (profiles != null && !profiles.isEmpty())
				return profiles;
			List<ProfileMapping> profileMappings = profile.getRequestedProfile();

			profileMappings.forEach(profileMapping -> {
				emptyList.add(profileRepository.findByProfileId(profileMapping.getRequestedProfile().getProfileId()));
			});

			// List<Profile> profiles = profileMappingRepository.getInterestedProfiles();

			return emptyList;

		} else {
			return emptyList;
		}

	}

	/**
	 * @author Sri Keerthna @since2019-12-12 This method take profileId as input and
	 *         if this profile has accepted by other id then it will get a list of
	 *         persons who accepted that particular id.
	 * @param profileId
	 * @return get a list of persons who accepted that particular id.
	 */
	@Override
	public List<Profile> getAcceptedProfiles(Integer profileId) {
		List<Profile> emptyList = new ArrayList<>();
		Profile profile = profileRepository.findByProfileId(profileId);
		if (profile != null) {
			List<Profile> profileMappings = profileMappingRepository.getAcceptedProfiles(profileId,
					StringConstant.ACCEPTED_STATUS);

			return profileMappings;
		} else {
			return emptyList;
		}
	}
}
