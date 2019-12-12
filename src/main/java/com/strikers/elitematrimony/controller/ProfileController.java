package com.strikers.elitematrimony.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.service.ProfileService;

/**
 * @author Vasavi
 * @description -> this class is used for to create a profile operation.
 * @since 2019-12-12
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/profiles")
public class ProfileController {
	/**
	 * The Constant log.
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	/**
	 * The profileService
	 */
	@Autowired
	ProfileService profileService;

	/**
	 * @description -> this method is used to create profile
	 * @param profileRequestDto
	 * @return profileResponseDto
	 * @throws AgeNotMatchedException
	 */
	@PostMapping()
	public ResponseEntity<ProfileResponseDto> createProfile(@RequestBody ProfileRequestDto profileRequestDto)
			throws AgeNotMatchedException {
		logger.info("Inside ProfileController:registerProfile ");
		ProfileResponseDto ProfileResponseDto = profileService.createProfile(profileRequestDto);
		return new ResponseEntity<>(ProfileResponseDto, HttpStatus.CREATED);

	}
}