package com.airtribe.learning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.airtribe.learning.model.TravelPackage;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    
    Page<TravelPackage> findByLocation(String location, Pageable pageable);
}
