package com.strikers.elitematrimony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.strikers.elitematrimony.dto.InterestedProfileDto;
import com.strikers.elitematrimony.dto.InterestedProfileResponseDto;
import com.strikers.elitematrimony.exception.MatrimonyServiceException;
import com.strikers.elitematrimony.service.InterestedProfileServiceImpl;

/**
 * @author vasavi
 * @since 2019-12-12
 * @description -> this class is used for to do test operation for interested profile
 *              controller.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class InterestedProfileControllerTest{
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProfileControllerTest.class);
	@InjectMocks
	InterestedProfileController interestedprofileController;

	@Mock
	InterestedProfileServiceImpl interestedProfileService;
	InterestedProfileResponseDto interestedProfileResponseDto = new InterestedProfileResponseDto();
	InterestedProfileDto interestedProfileDto = new InterestedProfileDto();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		interestedProfileResponseDto.setMessage("your interested submitted successfully");
		interestedProfileResponseDto.setStatusCode(201);
	}
	@Test
	public void testShowInterest() throws MatrimonyServiceException {
		logger.info("Inside showInterestTest method ");
		when(interestedProfileService.showInterest(interestedProfileDto)).thenReturn(interestedProfileResponseDto);
		ResponseEntity<InterestedProfileResponseDto> result = interestedprofileController.showInterest(interestedProfileDto);
		assertEquals("your interested submitted successfully", result.getBody().getMessage());
	}
	
	@Test
	public void testShowInterestNegative() throws MatrimonyServiceException {
		logger.info("Inside showInterestTest method ");
		InterestedProfileResponseDto interestedProfileResponseDtos = null;
		when(interestedProfileService.showInterest(interestedProfileDto)).thenReturn(interestedProfileResponseDtos);
		HttpStatus result = interestedprofileController.showInterest(interestedProfileDto).getStatusCode();
		assertEquals(HttpStatus.BAD_REQUEST, result);
	}
}
