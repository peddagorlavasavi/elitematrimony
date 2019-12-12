package com.strikers.elitematrimony.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.entity.ProfileMapping;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	Profile findByProfileId(Integer profileId);

=======
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	/**
	 * @description searchProfile is used to fetch the list of profile
	 * based on language, maritalStatus, qualification, profession, hobby or city
	 * @param searchKey
	 * @return List<Profile>
	 */
	@Query("select p from Profile p where p.language like %:searchKey% or p.qualification like %:searchKey% or p.maritalStatus like %:searchKey% or p.qualification like %:searchKey% or p.hobby like %:searchKey% or p.city like %:searchKey%")
	List<Profile> searchProfile(@Param("searchKey") String searchKey);
>>>>>>> 204cbe0af0575b6e3de6529facfecb145118719f

}
