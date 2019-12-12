package com.strikers.elitematrimony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.elitematrimony.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>{

}
