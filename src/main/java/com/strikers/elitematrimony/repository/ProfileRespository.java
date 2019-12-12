package com.strikers.elitematrimony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.Profile;

@Repository
public interface ProfileRespository extends JpaRepository<Profile, Integer> {

}
