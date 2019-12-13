package com.strikers.elitematrimony.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.elitematrimony.dto.GetStatusResponseDto;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.service.ProfileMappingService;
import com.strikers.elitematrimony.utils.StringConstant;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/profilemappings")
public class ProfileMappingController {

	@Autowired
	ProfileMappingService profileMappingService;

	private static final Logger logger = LoggerFactory.getLogger(ProfileMappingController.class);

	/**
	 * @author Sri Keerthna @since2019-12-12 This method take profileId as input and
	 *         get a list of persons who are interested on that particular id.
	 * @param profileId
	 * @return list of persons who are interested on a particular id.
	 */
	@GetMapping("{profileId}/myinterest")
	public ResponseEntity<GetStatusResponseDto> getMyInterestProfiles(@PathVariable("profileId") Integer profileId) {
		logger.info(profileId+"");
		GetStatusResponseDto getStatusResponseDto=new GetStatusResponseDto();
		List<Profile> interestedList = profileMappingService.getMyInterestProfiles(profileId);
		if(interestedList!=null && interestedList.isEmpty()) {
			getStatusResponseDto.setProfileMapping(interestedList);
			return new ResponseEntity<>(getStatusResponseDto, HttpStatus.OK);
		}else {
			logger.info("interested list is null or empty");
			getStatusResponseDto.setMessage(StringConstant.FAILED);
		return new ResponseEntity<>(getStatusResponseDto, HttpStatus.OK);
		}
	}

	/**
	 * @author Sri Keerthna @since2019-12-12 This method take profileId as input and
	 *         get a list of persons who accepted that particular id.
	 * @param profileId
	 * @return list of persons who are accepted for a particular id.
	 */
	@GetMapping("{profileId}/matching")
	public ResponseEntity<GetStatusResponseDto> getAcceptedProfiles(@PathVariable("profileId") Integer profileId) {
		List<Profile> acceptedList = profileMappingService.getAcceptedProfiles(profileId);
		GetStatusResponseDto getStatusResponseDto=new GetStatusResponseDto();
		if(acceptedList.isEmpty()) {
			getStatusResponseDto.setProfileMapping(acceptedList);
			return new ResponseEntity<>(getStatusResponseDto, HttpStatus.OK);
		}else {
			logger.info("interested list is null or empty");
			getStatusResponseDto.setMessage(StringConstant.FAILED);
		return new ResponseEntity<>(getStatusResponseDto, HttpStatus.OK);
		}
	}

	/**
	 * @author Sujal @since2019-12-12 This method take profileId as input and get a
	 *         list of persons who interested that particular id.
	 * @param profileId
	 * @return list of persons who are accepted for a particular id.
	 */
	@GetMapping("{profileId}/interested")
	public ResponseEntity<List<Profile>> getInterestedProfiles(@PathVariable("profileId") Integer profileId) {
		List<Profile> acceptedList = profileMappingService.getInterestedProfiles(profileId);
		if (acceptedList != null && !acceptedList.isEmpty()) {
			logger.info("Got the interested list");
			return new ResponseEntity<>(acceptedList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
