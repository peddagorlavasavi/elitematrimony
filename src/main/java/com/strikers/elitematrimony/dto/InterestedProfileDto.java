package com.strikers.elitematrimony.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InterestedProfileDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer profileId;
	private String status;
	private Integer interestedProfileId;
}
