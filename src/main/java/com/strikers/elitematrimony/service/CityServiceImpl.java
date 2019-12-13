package com.strikers.elitematrimony.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.controller.ProfileController;
import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.exception.CityNotFoundException;
import com.strikers.elitematrimony.repository.CityRepository;
import com.strikers.elitematrimony.utils.StringConstant;

/**
 * CityServiceImpl is used to implement CityService 
 * @author Hema
 * This controller is used to list all the cities 
 */
@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	CityRepository cityRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	/**
	 * @author Hema
	 * @description -> this method is used to list cities
	 * @return List<City>
	 * @throws CityNotFoundException
	 */
	@Override
	public List<City> listCity() throws CityNotFoundException  {
		logger.info("Listing the cities");
		List<City> cityList = cityRepository.findAll();
		if (cityList.isEmpty()) {
			throw new CityNotFoundException(StringConstant.CITY_NOT_FOUND);
		}
		return cityList;
	}

}
