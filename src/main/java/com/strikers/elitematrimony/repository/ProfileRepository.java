package com.strikers.elitematrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strikers.elitematrimony.entity.Profile;

	


public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	/**
	 * @description searchProfile is used to fetch the list of profile
	 * based on language, maritalStatus, qualification, profession, hobby or city
	 * @param searchKey
	 * @return List<Profile>
	 */
	@Query("select p from Profile p where p.language like %:searchKey% or p.qualification like %:searchKey% or p.maritalStatus like %:searchKey% or p.qualification like %:searchKey% or p.hobby like %:searchKey% or p.city like %:searchKey%")
	List<Profile> searchProfile(@Param("searchKey") String searchKey);
	
	/**
	 * @description searchProfile is used to fetch the list of profile
	 * based on language, maritalStatus, qualification, profession, hobby or city
	 * @param profileId 
	 * @return Profile
	 */
	Profile findByProfileId(Integer profileId);
	
	Profile findByMobileNumberAndPassword(String mobileNumber, String password);

	List<Profile> findByProfileIdNotAndGenderNotContains(Integer profileId, String gender);


}
