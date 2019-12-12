package com.strikers.elitematrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.ProfileMapping;

@Repository
public interface ProfileMappingRepository extends JpaRepository<ProfileMapping, Integer> {

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-12
	 * This query will filter the profile id.
	 * @param profileId
	 */
	@Query("select p from ProfileMapping p where p.requestedProfile.profileId =:profileId")
	List<ProfileMapping> getRequestedProfile(@Param("profileId") Integer profileId);

}
