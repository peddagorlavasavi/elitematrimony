package com.strikers.elitematrimony.service;

import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;

public interface ProfileService {
	public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) throws AgeNotMatchedException;

}
