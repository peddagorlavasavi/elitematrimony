package com.strikers.elitematrimony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strikers.elitematrimony.entity.City;
import com.strikers.elitematrimony.exception.CityNotFoundException;
import com.strikers.elitematrimony.repository.CityRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CityServiceTest {
	
	@InjectMocks
	CityServiceImpl cityServiceImpl;
	
	@Mock
	CityRepository cityRepository;
	
	static City city = new City();
	static List<City> cityList = new ArrayList<>();

	@Before
	public void setUp() {
		city.setCityId(1);
		city.setCityName("Chennai");
		cityList.add(city);
	}
	
	@Test
	public void testListCityPositive() throws CityNotFoundException {
		Mockito.when(cityRepository.findAll()).thenReturn(cityList);
		List<City> listCity = cityServiceImpl.listCity();
		assertEquals(1, listCity.size());
	}
	
	@Test(expected =CityNotFoundException.class )
	public void testListCityNegative() throws CityNotFoundException {
		List<City> cityLists = new ArrayList<>();
		Mockito.when(cityRepository.findAll()).thenReturn(cityLists);
		List<City> listCity = cityServiceImpl.listCity();
		assertEquals(0, listCity.size());
	}
	

}
