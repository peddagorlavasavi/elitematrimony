package com.strikers.elitematrimony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
