package com.airtribe.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtribe.learning.dto.TravelPackageRequestDTO;
import com.airtribe.learning.exception.ResourceNotFoundException;
import com.airtribe.learning.model.TravelPackage;
import com.airtribe.learning.repository.TravelPackageRepository;

@Service
public class TravelPackageService {

    private final TravelPackageRepository repository;

    @Autowired
    public TravelPackageService(TravelPackageRepository repository) {
        this.repository = repository;
    }

    public List<TravelPackage> getAllPackages() {
        return this.repository.findAll();
    }

    public Optional<TravelPackage> getPackageById(Long id) {
        return this.repository.findById(id);
    }

    public TravelPackage addPackage(TravelPackageRequestDTO request) {
        TravelPackage travelPackage = new TravelPackage(
            request.getTitle(),
            request.getDescription(),
            request.getPrice(),
            request.getDuration(),
            request.getLocation()
        );
        this.repository.save(travelPackage);
        return travelPackage;
    }

    public void deletePackageById(Long id) {
        if (!this.repository.existsById(id)) {
            throw new ResourceNotFoundException("Package not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
