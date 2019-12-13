package com.strikers.elitematrimony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.exception.CityNotFoundException;
import com.strikers.elitematrimony.repository.CityRepository;
import com.strikers.elitematrimony.utils.StringConstant;

@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	CityRepository cityRepository;
	
	/**
	 * @author Hema
	 * listCity is used to list cities
	 * @return
	 * @throws CityNotFoundException
	 */
	@Override
	public List<City> listCity() throws CityNotFoundException  {
		List<City> cityList = cityRepository.findAll();
		if (cityList.isEmpty()) {
			throw new CityNotFoundException(StringConstant.CITY_NOT_FOUND);
		}
		return cityList;
	}

}
