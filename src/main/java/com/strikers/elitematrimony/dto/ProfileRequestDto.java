package com.strikers.elitematrimony.dto;


import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.strikers.elitematrimony.entity.Profile;

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
	private LocalDate createdDate;
	private String status;
	private String description;
	private String profession;
	private String email;
	private String address;
	private Integer age;

}
