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

/**
 * Language is an entity class which list the languages
 * @author Hema
 *
 */
@Entity
@Table(name = "language")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Language {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer languageId;
	private String languageName;

}
