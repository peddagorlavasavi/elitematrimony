package com.strikers.elitematrimony.service;

import java.util.List;

import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.exception.CityNotFoundException;

public interface CityService {
	
	public List<City> listCity() throws CityNotFoundException;

}
