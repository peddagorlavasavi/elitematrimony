package com.strikers.elitematrimony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.entity.ProfileMapping;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	Profile findByProfileId(Integer profileId);


}
