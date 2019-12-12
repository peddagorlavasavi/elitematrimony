package com.strikers.elitematrimony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.elitematrimony.dto.LoginRequestDto;
import com.strikers.elitematrimony.dto.LoginResponseDto;
import com.strikers.elitematrimony.dto.SuggestedListRequestDto;
import com.strikers.elitematrimony.dto.SuggestedListResponseDto;
import com.strikers.elitematrimony.exception.ProfileNotFoundException;
import com.strikers.elitematrimony.service.ProfileService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/profiles")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	/**
	 * @author Hema
	 * userLogin is used to verify the user by getting the mobileNumber and password
	 * @param loginRequestDto
	 * @return
	 * @throws ProfileNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto loginRequestDto) throws ProfileNotFoundException{
		LoginResponseDto loginResponseDto=profileService.userLogin(loginRequestDto);
		if(loginResponseDto != null) {
			return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * @author Hema
	 * listProfile is used to list the profiles based on profileId and gender
	 * @param suggestedListRequestDto
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<List<SuggestedListResponseDto>> listProfile(SuggestedListRequestDto suggestedListRequestDto){
		List<SuggestedListResponseDto> profileList=profileService.suggestedList(suggestedListRequestDto);
		if (profileList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else { 
			return new ResponseEntity<>(profileList, HttpStatus.OK);
		}
	}

}
