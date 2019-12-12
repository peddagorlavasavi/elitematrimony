package com.strikers.elitematrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	Profile findByMobileNumberAndPassword(String mobileNumber, String password);

	List<Profile> findByProfileIdNotAndGenderNotContains(Integer profileId, String gender);




}
