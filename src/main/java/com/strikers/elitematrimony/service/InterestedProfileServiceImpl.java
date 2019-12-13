package com.strikers.elitematrimony.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.dto.InterestedProfileDto;
import com.strikers.elitematrimony.dto.InterestedProfileResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.entity.ProfileMapping;
import com.strikers.elitematrimony.exception.MatrimonyServiceException;
import com.strikers.elitematrimony.repository.ProfileMappingRepository;
import com.strikers.elitematrimony.repository.ProfileRepository;
import com.strikers.elitematrimony.utils.StringConstant;
import com.strikers.elitematrimony.utils.Utils;

/**
 * @author vasavi
 * @since 2019-12-12
 * @description -> this class is used to show interest on a particular profileId
 *
 */
@Service
public class InterestedProfileServiceImpl implements InterestedProfileService {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(InterestedProfileServiceImpl.class);
	/**
	 * The profileRepository
	 */
	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	ProfileMappingRepository profileMappingRepository;

	/**
	 * @description -> this method is used for to show interest on profile.
	 * @param interestedProfileDto : which contains profiledId,status and
	 *                             interestedProfileId.
	 * @return interestedProfileResponseDto
	 * @throws MatrimonyServiceException
	 */
	@Override
	public InterestedProfileResponseDto showInterest(InterestedProfileDto interestedProfileDto)
			throws MatrimonyServiceException {
		logger.info("Inside InterestedProfileServiceImpl :showInterest" + interestedProfileDto.getProfileId());
		InterestedProfileResponseDto interestedProfileResponseDto = null;

		if (interestedProfileDto != null) {
			logger.info("Inside InterestedProfileServiceImpl :showInterest 99999999999 " + interestedProfileDto.getProfileId());

			Profile profile = profileRepository.findByProfileId(interestedProfileDto.getProfileId());
			Profile intrestedProfile = profileRepository.findByProfileId(interestedProfileDto.getInterestedProfileId());
			if (profile != null && intrestedProfile != null) {
				if (interestedProfileDto.getStatus().equalsIgnoreCase(StringConstant.INTERESTED_STATUS)) {
					interestedProfileResponseDto = new InterestedProfileResponseDto();
					ProfileMapping profileMapping = new ProfileMapping();
					profileMapping.setRequestedProfile(profile);
					profileMapping.setRequestedDate(Utils.getCurrentDate());
					profileMapping.setAcceptedStatus(StringConstant.INTERESTED_STATUS);
					profileMapping.setInterestedProfile(intrestedProfile);
					profileMappingRepository.save(profileMapping);

					interestedProfileResponseDto.setMessage(StringConstant.MESSAGE_SUCCESS);
					interestedProfileResponseDto.setStatusCode(200);
				} else if (interestedProfileDto.getStatus().equalsIgnoreCase(StringConstant.ACCEPTED_STATUS)) {
					logger.info("Inside InterestedProfileServiceImpl :showInteres00000000000t" + interestedProfileDto.getProfileId());

					interestedProfileResponseDto = new InterestedProfileResponseDto();
					ProfileMapping profileMapping = profileMappingRepository.getByProfileIdAndAcceptedStatus(
							interestedProfileDto.getProfileId(), interestedProfileDto.getInterestedProfileId(), StringConstant.INTERESTED_STATUS);
					profileMapping.setAcceptedStatus(StringConstant.ACCEPTED_STATUS);
					profileMapping.setAcceptedDate(Utils.getCurrentDate());
					profileMappingRepository.save(profileMapping);

					interestedProfileResponseDto.setMessage(StringConstant.MESSAGE_SUCCESS);
					interestedProfileResponseDto.setStatusCode(201);

				} else {
					ProfileMapping profileMapping = profileMappingRepository.getByProfileIdAndAcceptedStatus(
							interestedProfileDto.getProfileId(), interestedProfileDto.getInterestedProfileId(),
							StringConstant.INTERESTED_STATUS);
					interestedProfileResponseDto = new InterestedProfileResponseDto();
					profileMapping.setAcceptedStatus(StringConstant.NOT_INTERESTED_STATUS);
					profileMapping.setAcceptedDate(Utils.getCurrentDate());
					profileMappingRepository.save(profileMapping);

					interestedProfileResponseDto.setMessage(StringConstant.FAILED);
					interestedProfileResponseDto.setStatusCode(201);
				}
			} else {
				throw new MatrimonyServiceException(StringConstant.PROFILE_NOT_FOUND);
			}
		}
		return interestedProfileResponseDto;
	}

}
