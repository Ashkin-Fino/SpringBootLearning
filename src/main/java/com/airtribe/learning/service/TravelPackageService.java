package com.airtribe.learning.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<TravelPackage> getAllPackages(String location, Pageable pageable) {
        if (location != null) {
            return repository.findByLocation(location, pageable);
        }
        return repository.findAll(pageable);
    }

    public TravelPackage getPackageById(Long id) {
        Optional<TravelPackage> travelPackage = this.repository.findById(id);
        if (travelPackage.isEmpty()) {
            throw new ResourceNotFoundException("Package not found with id: " + id);
        }
        return travelPackage.get();
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

    public TravelPackage updatePackage(Long id, TravelPackageRequestDTO request) {
        TravelPackage existing = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Package not found with id: " + id)
            );
        
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setDuration(request.getDuration());
        existing.setLocation(request.getLocation());

        return repository.save(existing);
    }

    public TravelPackage patchPackage(Long id, Map<String, Object> updates) {

        TravelPackage existing = this.repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Package not found with id: " + id)
                );
    
        updates.forEach((key, value) -> {
            switch (key) {
                case "title" -> existing.setTitle((String) value);
                case "description" -> existing.setDescription((String) value);
                case "price" -> existing.setPrice(Double.parseDouble(value.toString()));
                case "duration" -> existing.setDuration((String) value);
                case "location" -> existing.setLocation((String) value);
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
    
        return this.repository.save(existing);
    }
}
