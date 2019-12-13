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
	 * @param gender 
	 * @return List<Profile>
	 */
	@Query("select p from Profile p where (p.language like %:searchKey% or p.qualification like %:searchKey% or p.maritalStatus like %:searchKey% or p.qualification like %:searchKey% or p.hobby like %:searchKey% or p.city like %:searchKey%) and (p.gender !=:gender)")
	List<Profile> searchProfile(@Param("searchKey") String searchKey, @Param("gender")  String gender);
	
	/**
	 * @description searchProfile is used to fetch the list of profile
	 * based on language, maritalStatus, qualification, profession, hobby or city
	 * @param profileId 
	 * @return Profile
	 */
	@Query("select p from Profile p where p.profileId=:profileId and p.status=:status")
	Profile findByProfileId(@Param("profileId") Integer profileId, @Param("status") String status);
	
	Profile findByMobileNumberAndPassword(String mobileNumber, String password);

	List<Profile> findByProfileIdNotAndGenderNotContains(Integer profileId, String gender);

	@Query("select p from Profile p where p.profileId !=:profileId and p.gender !=:gender ")
	List<Profile> getSuggestedProfiles(@Param("profileId") Integer profileId, @Param("gender") String gender);

}

