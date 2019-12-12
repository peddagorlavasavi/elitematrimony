package com.strikers.elitematrimony.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "city")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cityId;
	private String cityName;

}
