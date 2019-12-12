package com.strikers.elitematrimony.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProfileResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer profileId;
	private String message;
	private Integer statusCode;

}
