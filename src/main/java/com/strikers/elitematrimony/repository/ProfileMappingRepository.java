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
	@Query(nativeQuery = true, value="select * from profile p where p.profile_id in (select pm.requested_profile_id from profile_mapping pm where pm.interested_profile_id=:profileId and pm.accepted_status=:status)")
	List<Object[]> getInterestedProfiles(@Param("profileId") Integer profileId, @Param("status") String status);

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-11 
	 * @param profileId for getting particular profile id
	 * @param status for that particular id status with interested 
	 * @return will return the status of that particular id
	 */
	@Query(nativeQuery = true, value="select * from profile p where p.profile_id in (select pm.interested_profile_id from profile_mapping pm where pm.requested_profile_id=:profileId and pm.accepted_status=:status)")
	List<Object[]> getMyInterestProfiles(@Param("profileId") Integer profileId, @Param("status") String status);

	
	/**
	 * @author Sri Keerthna
	 * @since 2019-12-11 
	 * @param profileId for getting particular profile id
	 * @param status for that particular id status with accepted
	 * @return will return the status of that particular id
	 */
	@Query("select p from Profile p inner join p.interestedProfiles pi inner join p.requestedProfiles pr where (pi.profileMappingId =:profileId or pr.profileMappingId =:profileId ) and (pi.acceptedStatus =:status or pr.acceptedStatus =:status)")
	List<Profile> getAcceptedProfiles(@Param("profileId") Integer profileId,  @Param("status") String status);

	/**
	 * @author Sujal
	 * @since 2019-12-11 
	 * @description This method is used to fetch the profiles with status accepted
	 * @param profileId for getting particular profile id
	 * @param status for that particular id status with accepted
	 * @return ProfileMapping It will return the status of that particular id
	 */
	@Query("select p from ProfileMapping p where p.requestedProfile.profileId =:interestedProfileId and p.interestedProfile.profileId =:profileId and p.acceptedStatus =:status")
	ProfileMapping getByProfileIdAndAcceptedStatusToAccept(@Param("profileId") Integer profileId, @Param("interestedProfileId") Integer interestedProfileId, @Param("status") String status);

	/**
	 * @author Sujal
	 * @since 2019-12-11 
	 * @description This method is used to fetch the profiles with status both interested and accepted
	 * @param profileId for getting particular profile id
	 * @param status for that particular id status with accepted
	 * @return will return the status of that particular id
	 */
	@Query("select p from ProfileMapping p where p.requestedProfile.profileId =:profileId and p.interestedProfile.profileId =:interestedProfileId and (p.acceptedStatus =:interestedStatus or p.acceptedStatus =:acceptedStatus)")
	ProfileMapping getByProfileIdAndAcceptedStatusToInterest(@Param("profileId") Integer profileId, @Param("interestedProfileId") Integer interestedProfileId, @Param("interestedStatus") String interestedStatus, @Param("acceptedStatus") String acceptedStatus);

	/**
	 * @author Sujal
	 * @since 2019-12-11 
	 * @description This method is used to fetch the profiles with status interested
	 * @param profileId for getting particular profile id
	 * @param status for that particular id status with accepted
	 * @return will return the status of that particular id
	 */
	@Query("select p from ProfileMapping p where p.requestedProfile.profileId =:profileId and p.interestedProfile.profileId =:interestedProfileId and p.acceptedStatus =:interestedStatus")
	ProfileMapping getByProfileIdAndAcceptedStatus(@Param("profileId") Integer profileId, @Param("interestedProfileId") Integer interestedProfileId, @Param("interestedStatus") String interestedStatus);

}
