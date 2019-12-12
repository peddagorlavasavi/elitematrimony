package com.strikers.elitematrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.entity.ProfileMapping;

@Repository
public interface ProfileMappingRepository extends JpaRepository<ProfileMapping, Integer> {
	
	/**
	 * @description @author Sujal This method is used to fetch the list of profiles which is shown
	 *              interested to the given profile.
	 * @param profileId
	 * @param status
	 * @return List<Profile>
	 */
	@Query("select p from Profile p inner join p.interestedProfiles pi where pi.profileMappingId =:profileId and pi.acceptedStatus =:status")
	List<Profile> getInterestedProfiles(@Param("profileId") Integer profileId, @Param("status") String status);

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-11 
	 * @param profileId for getting particular profile id
	 * @param status for that particular id status with interested 
	 * @return will return the status of that particular id
	 */
	@Query("select p from Profile p inner join p.requestedProfile pr where pr.profileMappingId =:profileId and pr.acceptedStatus =:status")
	List<Profile> getMyInterestProfiles(@Param("profileId") Integer profileId, @Param("status") String status);

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-11 
	 * @param profileId for getting particular profile id
	 * @param status for that particular id status with accepted
	 * @return will return the status of that particular id
	 */
	@Query("select p from Profile p inner join p.interestedProfiles pi inner join p.requestedProfile pr where (pi.profileMappingId =:profileId or pr.profileMappingId =:profileId ) and (pi.acceptedStatus =:status or pr.acceptedStatus =:status)")
	List<Profile> getAcceptedProfiles(@Param("profileId") Integer profileId,  @Param("status") String status);

}
