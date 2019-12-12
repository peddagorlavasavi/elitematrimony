package com.strikers.elitematrimony.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProfileMappingRequestDto {

	Integer profileId;
	Integer interestedProfileId;
	String interestStatus;
}
