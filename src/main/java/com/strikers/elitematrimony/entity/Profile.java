package com.strikers.elitematrimony.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer profileId;
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

	@JsonManagedReference
	@OneToMany(mappedBy = "requestedProfile")
	private List<ProfileMapping> requestedProfile;

	@JsonManagedReference
	@OneToMany(mappedBy = "interestedProfile")
	private List<ProfileMapping> interestedProfiles;

}
