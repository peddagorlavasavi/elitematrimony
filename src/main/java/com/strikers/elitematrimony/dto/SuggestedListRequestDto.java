package com.strikers.elitematrimony.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestedListRequestDto  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer profileId;
	private String gender;

}
