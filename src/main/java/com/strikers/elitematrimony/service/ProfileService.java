package com.strikers.elitematrimony.service;

import java.util.List;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;

public interface ProfileService {

	List<Profile> searchProfile(String searchKey, String gender);

	public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) throws AgeNotMatchedException;

	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws ProfileNotFoundException;

	List<SuggestedListResponseDto> suggestedList(SuggestedListRequestDto suggestedListRequestDto);


}
