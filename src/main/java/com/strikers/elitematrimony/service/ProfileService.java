package com.strikers.elitematrimony.service;

import java.util.List;

import com.strikers.elitematrimony.entity.Profile;

public interface ProfileService {

	List<Profile> searchProfile(String searchKey);

}
