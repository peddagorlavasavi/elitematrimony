package com.strikers.elitematrimony.service;

import java.util.List;

import com.strikers.elitematrimony.entity.ProfileMapping;

public interface ProfileMappingService {

	public List<ProfileMapping> showInterestedProfiles(Integer profileId);
	
	public List<ProfileMapping> showAcceptedProfiles(Integer profileId);
}
