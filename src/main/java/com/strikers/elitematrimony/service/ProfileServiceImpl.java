package com.strikers.elitematrimony.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.controller.ProfileController;
import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;
import com.strikers.elitematrimony.repository.CityRepository;
import com.strikers.elitematrimony.repository.LanguageRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.util.StringConstant;
import com.strikers.elitematrimony.util.Utils;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private CityRepository cityRepository;

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
	

	@Override
	public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) throws AgeNotMatchedException {
		logger.info("In registerProfile() method");
		ProfileResponseDto profileResponseDto = new ProfileResponseDto();

		if (profileRequestDto != null) {
			logger.info("" + Utils.calculateAge(profileRequestDto.getDob()));
			if (Utils.calculateAge(profileRequestDto.getDob()) >= StringConstant.MIN_AGE) {
				Profile profile = new Profile();
				BeanUtils.copyProperties(profileRequestDto, profile);
				
				Optional<City> optionalCity= cityRepository.findById(Integer.parseInt(profileRequestDto.getCity()));
				if(optionalCity.isPresent()) {
					profile.setCity(optionalCity.get().getCityName());
				}
				
				Optional<Language> optionalLanguage = languageRepository.findById(Integer.parseInt(profileRequestDto.getLanguage()));
				if(optionalLanguage.isPresent()) {
					profile.setLanguage(optionalLanguage.get().getLanguageName());
				}
				
				profile = profileRepository.save(profile);
				profileResponseDto.setProfileId(profile.getProfileId());
				profileResponseDto.setMessage(StringConstant.SUCCESS);
				profileResponseDto.setStatusCode(200);
				return profileResponseDto;
			} else {
				throw new AgeNotMatchedException(StringConstant.AGE_VALIDATION_FAILED);

			}
		} else {
			profileResponseDto.setMessage(StringConstant.FAILED);
			profileResponseDto.setStatusCode(200);
			return profileResponseDto;
		}
	}

	/**
	 * @author Hema
	 * userLogin is used to verify the user by getting the mobileNumber and password
	 * @param loginRequestDto
	 * @return
	 * @throws ProfileNotFoundException
	 */
	@Override
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws ProfileNotFoundException {
		logger.info("Verify the user");
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
		logger.info("Showing profiles based on gender");
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
