package com.strikers.elitematrimony.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.repository.CityRepository;
import com.strikers.elitematrimony.repository.LanguageRepository;
import com.strikers.elitematrimony.util.StringConstant;
import com.strikers.elitematrimony.util.Utils;
import com.strikers.elitematrimony.controller.ProfileController;
import com.strikers.elitematrimony.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private ProfileRepository profileRepository;
	
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
	

	/**
	 *@description -> this method is used to create profile.
	 *@param profileRequestDto : which contains the fields of profile
	 *@return profileResponseDto
	 *@throws AgeNotMatchedException
	 */
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
}
