package com.strikers.elitematrimony.service;

import com.strikers.elitematrimony.dto.InterestedProfileDto;
import com.strikers.elitematrimony.dto.InterestedProfileResponseDto;
import com.strikers.elitematrimony.exception.MatrimonyServiceException;

public interface InterestedProfileService {
	public InterestedProfileResponseDto showInterest(InterestedProfileDto interestedProfileDto)
			throws MatrimonyServiceException;

}
