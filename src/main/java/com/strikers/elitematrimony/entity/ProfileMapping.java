package com.strikers.elitematrimony.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile_mapping")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer profileMappingId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="requested_profile_id")
	private Profile requestedProfile;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="interested_profile_id")
	private Profile interestedProfile;
	
	private LocalDate requestedDate;
	private LocalDate acceptedDate;
	private String acceptedStatus;

}
