package com.strikers.elitematrimony.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.elitematrimony.dto.InterestedProfileDto;
import com.strikers.elitematrimony.dto.InterestedProfileResponseDto;
import com.strikers.elitematrimony.exception.MatrimonyServiceException;
import com.strikers.elitematrimony.service.InterestedProfileService;

/**
 * @author Vasavi
 * @since 2019-12-12
 * @description -> this class is used for to show interest on profile.
 */
@RestController
@RequestMapping("/intrestedprofiles")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class InterestedProfileController {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(InterestedProfileController.class);
	/**
	 * The interestedProfileService
	 */
	@Autowired
	InterestedProfileService interestedProfileService;

	/**
	 * @description -> this method is used for to show interest on profile
	 * @param interestedProfileDto
	 * @return interestedProfileResponseDto
	 * @throws MatrimonyServiceException 
	 */
	@PostMapping()
	public ResponseEntity<InterestedProfileResponseDto> showInterest(InterestedProfileDto interestedProfileDto) throws MatrimonyServiceException {
		logger.info("Inside InterestedProfileController:shoeInterest");
		InterestedProfileResponseDto interestedProfileResponseDto = interestedProfileService
				.showInterest(interestedProfileDto);
		return new ResponseEntity<>(interestedProfileResponseDto, HttpStatus.ACCEPTED);

	}
}
