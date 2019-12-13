package com.strikers.elitematrimony.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.repository.ProfileMappingRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.utils.ProfileComposer;
import com.strikers.elitematrimony.utils.StringConstant;

@Service
public class ProfileMappingServiceImpl implements ProfileMappingService {

	private static final Logger logger = LoggerFactory.getLogger(ProfileMappingServiceImpl.class);

	@Autowired
	ProfileMappingRepository profileMappingRepository;

	@Autowired
	ProfileRepository profileRepository;

	@Qualifier("profileComposer")
	@Autowired
	ProfileComposer<List<Object[]>, List<Profile>> profileComposer;

	/**
	 * @description @author Sujal @since2019-12-12 This method take profileId as
	 *              input and if this profile is shown in interested by other
	 *              profile.
	 * @param profileId
	 * @return get a list of persons who are interested on the given profile.
	 */
	@Override
	public List<Profile> getInterestedProfiles(Integer profileId) {
		Profile profile = profileRepository.findByProfileId(profileId, StringConstant.ACTIVE_STATUS);
		if (profile != null) {
			List<Object[]> objects = profileMappingRepository.getInterestedProfiles(profileId,
					StringConstant.INTERESTED_STATUS);

			List<Profile> profiles = profileComposer.compose(objects);
			logger.info("Got all the interested profiles");

			return profiles;
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * @author Sri Keerthna & Sujal @since2019-12-12 This method take profileId as input and
	 *         if that profile has any request for an interest. It will get a list
	 *         of persons who are interested on that particular id.
	 * @param profileId
	 * @return list of persons who are interested on a particular id.
	 */
	@Override
	public List<Profile> getMyInterestProfiles(Integer profileId) {
		List<Profile> emptyList = new ArrayList<>();
		Profile profile = profileRepository.findByProfileId(profileId, StringConstant.ACTIVE_STATUS);
		if (profile != null) {

			List<Object[]> objects = profileMappingRepository.getMyInterestProfiles(profileId,
					StringConstant.INTERESTED_STATUS);

			List<Profile> profiles = profileComposer.compose(objects);
			logger.info("Got my interested profiles");
			return profiles;

		} else {
			return emptyList;
		}

	}

	/**
	 * @author Sri Keerthna & Sujal @since2019-12-12 This method take profileId as input and
	 *         if this profile has accepted by other id then it will get a list of
	 *         persons who accepted that particular id.
	 * @param profileId
	 * @return get a list of persons who accepted that particular id.
	 */
	@Override
	public List<Profile> getAcceptedProfiles(Integer profileId) {
		List<Profile> emptyList = new ArrayList<>();
		Profile profile = profileRepository.findByProfileId(profileId, StringConstant.ACTIVE_STATUS);
		if (profile != null) {
	
			List<Object[]> objectList = profileMappingRepository.getMyInterestProfiles(profileId,
					StringConstant.ACCEPTED_STATUS);
			
			List<Object[]> objects = profileMappingRepository.getInterestedProfiles(profileId,
					StringConstant.ACCEPTED_STATUS);

			List<Profile> profileList = profileComposer.compose(objectList);

			List<Profile> profiles = profileComposer.compose(objects);
			
			if(profileList!=null)
				emptyList.addAll(profileList);
			if(profiles!=null)
				emptyList.addAll(profiles);

			return emptyList;
		} else {
			return emptyList;
		}
	}
}
