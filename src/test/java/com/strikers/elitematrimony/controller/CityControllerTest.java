
package com.strikers.elitematrimony.controller;

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
import com.strikers.elitematrimony.service.CityService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CityControllerTest {

	@InjectMocks
	CityController cityController;

	@Mock
	CityService cityService;

	static City city = new City();
	static List<City> cityList = new ArrayList<>();

	@Before
	public void setUp() {
		city.setCityId(1);
		city.setCityName("Chennai");
		cityList.add(city);
	}
	
	@Test
	public void testListCityPositive() throws CityNotFoundException{
		Mockito.when(cityService.listCity()).thenReturn(cityList);
		Integer result = cityController.listCity().getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testListCityNegative() throws CityNotFoundException {
		List<City> cityLists = new ArrayList<>();
		Mockito.when(cityService.listCity()).thenReturn(cityLists);
		Integer result = cityController.listCity().getStatusCodeValue();
		assertEquals(204, result);
	}


}
