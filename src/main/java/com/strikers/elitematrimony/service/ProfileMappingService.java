package com.strikers.elitematrimony.service;

import java.util.List;

import com.strikers.elitematrimony.entity.Profile;

public interface ProfileMappingService {

	public List<Profile> getMyInterestProfiles(Integer profileId);
	
	public List<Profile> getAcceptedProfiles(Integer profileId);

	public List<Profile> getInterestedProfiles(Integer profileId);

}
