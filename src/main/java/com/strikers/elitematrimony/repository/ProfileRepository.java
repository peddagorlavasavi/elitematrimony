package com.strikers.elitematrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strikers.elitematrimony.entity.Profile;
import com.strikers.elitematrimony.utils.StringConstant;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	/**
	 * @author Sujal
	 * @description searchProfile is used to fetch the list of profile based on
	 *              firstname, lastname, language, maritalStatus, qualification,
	 *              profession, hobby or city
	 * @param searchKey search key can anything of the above mentioned properties
	 * @param gender
	 * @return List<Profile>
	 */
	@Query("select p from Profile p where (p.firstName like %:searchKey% or p.lastName like %:searchKey% or p.language like %:searchKey% or p.qualification like %:searchKey% or p.maritalStatus like %:searchKey% or p.qualification like %:searchKey% or p.hobby like %:searchKey% or p.city like %:searchKey%) and (p.gender !=:gender)")
	List<Profile> searchProfile(@Param("searchKey") String searchKey, @Param("gender") String gender);

	/**
	 * @author Sujal
	 * @description searchProfile is used to fetch the list of profile based on
	 *              language, maritalStatus, qualification, profession, hobby or
	 *              city
	 * @param profileId
	 * @return Profile
	 */
	@Query("select p from Profile p where p.profileId=:profileId and p.status=:status")
	Profile findByProfileId(@Param("profileId") Integer profileId, @Param("status") String status);

	/**
	 * @author Hema
	 * @description This method will fetch data based on mobile number and password
	 * @param mobileNumber
	 * @param password
	 * @return Profile
	 */
	Profile findByMobileNumberAndPassword(String mobileNumber, String password);

	/**
	 * @author Hema
	 * @description This method will fetch profiles where profile id and gender
	 *              should be same
	 * @param profileId
	 * @param gender
	 * @return List<Profile> is the list of profiles
	 */
	List<Profile> findByProfileIdNotAndGenderNotContains(Integer profileId, String gender);

	/**
	 * @author Hema
	 * @description This method will fetch profiles where profile id and gender
	 *              should be same
	 * @param profileId
	 * @param gender
	 * @return
	 */
	@Query("select p from Profile p where p.profileId !=:profileId and p.gender !=:gender ")
	List<Profile> getSuggestedProfiles(@Param("profileId") Integer profileId, @Param("gender") String gender);

	/**
	 * @author Sujal
	 * @description This method will fetch profiles where profile id and gender
	 *              should be same and the profiles status should not be in not
	 *              interested status
	 * @param profileId
	 * @param gender
	 * @param status
	 * @return list of profile
	 */
	@Query(nativeQuery = true, value = "select p.* from profile p where p.profile_id !=:profileId and p.gender !=:gender"
			+ " and p.profile_id not in ( select pm.requested_profile_id from profile_mapping pm where pm.interested_profile_id =:profileId and pm.accepted_status='"+StringConstant.NOT_INTERESTED_STATUS+"')"
			+ " and p.profile_id not in ( select pm.interested_profile_id from profile_mapping pm where pm.requested_profile_id =:profileId and pm.accepted_status='"+StringConstant.INTERESTED_STATUS+"')"
			+ " and p.profile_id not in ( select pm.requested_profile_id from profile_mapping pm where pm.interested_profile_id =:profileId and pm.accepted_status='"+StringConstant.ACCEPTED_STATUS+"')"
			+ " and p.profile_id not in ( select pm.interested_profile_id from profile_mapping pm where pm.requested_profile_id =:profileId and pm.accepted_status='"+StringConstant.ACCEPTED_STATUS+"') "
			+ " order by p.profile_id desc")
	List<Profile> getSuggestedProfileList(@Param("profileId") Integer profileId, @Param("gender") String gender);

}
