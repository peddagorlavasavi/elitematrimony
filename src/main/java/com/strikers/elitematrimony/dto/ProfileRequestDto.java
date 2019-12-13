package com.strikers.elitematrimony.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProfileRequestDto {

	private String firstName;
	private String lastName;
	private String gender;
	private String language;
	private String qualification;
	private LocalDate dob;
	private Double monthlyIncome;
	private String mobileNumber;
	private String city;
	private String hobby;
	private String maritalStatus;
	private String userName;
	private String password;
	private String description;
	private String profession;
	private String email;
	private String address;
	private Integer age;

}
