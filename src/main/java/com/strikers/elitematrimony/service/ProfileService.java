package com.strikers.elitematrimony.service;

import java.util.List;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;

public interface ProfileService {
	
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws ProfileNotFoundException;
	
	List<SuggestedListResponseDto> suggestedList(SuggestedListRequestDto suggestedListRequestDto);
	
}
