package com.strikers.elitematrimony.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.controller.ProfileController;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private ProfileRepository profileRepository;

	/**
	 * @description This method is used to search profile based on language,
	 *              maritalStatus, qualification, profession, hobby or city
	 * @param searchKey is used to search the above mentioned field of profile
	 * @return List<Profile> is the list of profile
	 */
	@Override
	public List<Profile> searchProfile(String searchKey) {
		logger.info("inside Profile Service");
		return profileRepository.searchProfile(searchKey);
	}
}
