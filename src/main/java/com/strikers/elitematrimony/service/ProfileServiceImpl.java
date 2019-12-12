package com.strikers.elitematrimony.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.util.StringConstant;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepository;

	/**
	 * @author Hema
	 * userLogin is used to verify the user by getting the mobileNumber and password
	 * @param loginRequestDto
	 * @return
	 * @throws ProfileNotFoundException
	 */
	@Override
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws ProfileNotFoundException {
		Profile profile = profileRepository.findByMobileNumberAndPassword(loginRequestDto.getMobileNumber(),
				loginRequestDto.getPassword());
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		if (profile != null) {
			BeanUtils.copyProperties(profile, loginResponseDto);
			loginResponseDto.setMessage(StringConstant.LOGIN_SUCCESS);
		} else {
			throw new ProfileNotFoundException(StringConstant.NO_PROFILE);
		}
		return loginResponseDto;
	}

	/**
	 * @author Hema
	 * listProfile is used to list the profiles based on profileId and gender
	 * @param suggestedListRequestDto
	 * @return
	 */
	@Override
	public List<SuggestedListResponseDto> suggestedList(SuggestedListRequestDto suggestedListRequestDto) {
		List<Profile> profiles=profileRepository.findByProfileIdNotAndGenderNotContains(suggestedListRequestDto.getProfileId(),suggestedListRequestDto.getGender());
		List<SuggestedListResponseDto> suggestedListResponseDtos= new ArrayList<>();
		profiles.forEach(profile ->{
			SuggestedListResponseDto suggestedListResponseDto= new SuggestedListResponseDto();
			BeanUtils.copyProperties(profile, suggestedListResponseDto);
			suggestedListResponseDtos.add(suggestedListResponseDto);
		});
		return suggestedListResponseDtos;
	}

}
