package com.strikers.elitematrimony.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProfileRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private Integer age;
	private String address;
	private String email;

}
