package com.strikers.elitematrimony.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuggestedListResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate dob;
	private String language;
	private String qualification;
	private String profession;
	private Double monthlyIncome;
	private String mobileNumber;
	private String city;
	private String hobby;
	private String maritalStatus;
	private String status;
	private String description;
	private String email;
	private String address;
	private Integer age;
	private Integer profileId;

}
