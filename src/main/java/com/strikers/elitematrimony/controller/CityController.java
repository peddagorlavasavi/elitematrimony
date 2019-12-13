package com.strikers.elitematrimony.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.exception.CityNotFoundException;
import com.strikers.elitematrimony.service.CityService;

/**
 * CityController is the controller used to show the list of cities
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/cities")
public class CityController {

	@Autowired
	CityService cityService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	/**
	 * @author Hema
	 * @description -> this method is used to list cities
	 * @return <List<City>>
	 * @throws CityNotFoundException
	 */
	@GetMapping()
	public ResponseEntity<List<City>> listCity() throws CityNotFoundException {
		logger.info("Listing all the Cities");
		List<City> cityList = cityService.listCity();
		if (cityList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(cityList, HttpStatus.OK);
		}
	}

}
