package com.strikers.elitematrimony.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strikers.elitematrimony.repository.ProfileRepository;


@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceImplTest {
	@InjectMocks
	ProfileServiceImpl profileServiceImpl;
	@Mock
	 ProfileRepository profileRespository;
	

}
