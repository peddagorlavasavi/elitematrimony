package com.strikers.elitematrimony.dto;

import java.util.List;

import com.strikers.elitematrimony.entity.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetStatusResponseDto {

	List<Profile> profileMapping;
}
