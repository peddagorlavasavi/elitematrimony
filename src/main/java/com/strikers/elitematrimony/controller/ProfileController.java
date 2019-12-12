package com.strikers.elitematrimony.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.strikers.elitematrimony.dto.ProfileRequestDto;
import com.strikers.elitematrimony.dto.ProfileResponseDto;
import com.strikers.elitematrimony.exception.AgeNotMatchedException;
import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.service.ProfileService;

@RestController
@RequestMapping("/profiles")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	/**
	 * @description searchProfile is used to search profile based on language,
	 *              maritalStatus, qualification, profession, hobby or city
	 * @param searchKey is used to search the above mentioned field of profile
	 * @return List<Profile> is the list of profile
	 */
	@GetMapping()
	public ResponseEntity<List<Profile>> searchProfile(@RequestParam("searchKey") String searchKey) {
		List<Profile> profiles = profileService.searchProfile(searchKey);
		if (profiles != null) {
			logger.info("search result found");
			return new ResponseEntity<>(profiles, HttpStatus.OK);
		} else {
			logger.error("search result not found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

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
		ProfileResponseDto profileResponseDto = profileService.createProfile(profileRequestDto);
		return new ResponseEntity<>(profileResponseDto, HttpStatus.CREATED);

	}
}
