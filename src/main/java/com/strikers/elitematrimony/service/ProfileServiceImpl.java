package com.strikers.elitematrimony.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.entity.Language;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.repository.CityRepository;
import com.strikers.elitematrimony.repository.LanguageRepository;
import com.strikers.elitematrimony.repository.ProfileRespository;
import com.strikers.elitematrimony.util.StringConstant;
import com.strikers.elitematrimony.util.Utils;

@Service
public class ProfileServiceImpl implements ProfileService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
	
	@Autowired
	ProfileRespository profileRespository;
	
	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	CityRepository cityRepository;

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
				
				profile = profileRespository.save(profile);
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
