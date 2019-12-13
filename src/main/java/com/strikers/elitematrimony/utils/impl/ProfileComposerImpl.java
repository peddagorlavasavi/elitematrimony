package com.strikers.elitematrimony.utils.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.utils.ProfileComposer;

@Component("profileComposer")
public class ProfileComposerImpl implements ProfileComposer<List<Object[]>, List<Profile>> {

	private static final Logger logger = LoggerFactory.getLogger(ProfileComposerImpl.class);

	@Override
	public List<Profile> compose(List<Object[]> objectList) {
		List<Profile> profiles = new ArrayList<Profile>();

		objectList.forEach(object -> {

			if (object != null && object.length != 0) {
				logger.info("At composer profile id");
				Profile profile = new Profile();

				profile.setProfileId(Integer.parseInt(object[0].toString()));
				profile.setAddress(object[1].toString());
				profile.setAge(Integer.parseInt(object[2].toString()));
				profile.setCity(object[3].toString());
				profile.setCreatedDate(LocalDate.parse(object[4].toString()));
				profile.setDescription(object[5].toString());
				profile.setDob(LocalDate.parse(object[6].toString()));
				profile.setEmail(object[7].toString());
				profile.setFirstName(object[8].toString());
				profile.setGender(object[9].toString());
				profile.setHobby(object[10].toString());
				profile.setLanguage(object[11].toString());
				profile.setLastName(object[12].toString());
				profile.setMaritalStatus(object[13].toString());
				profile.setMobileNumber(object[14].toString());
				profile.setMonthlyIncome(Double.valueOf(object[15].toString()));
				profile.setPassword(object[16].toString());
				profile.setProfession(object[17].toString());
				profile.setQualification(object[18].toString());
				profile.setStatus((object[19].toString()));
				profile.setUserName((object[20].toString()));
				profiles.add(profile);

			}
		});

		return profiles;
	}

}
