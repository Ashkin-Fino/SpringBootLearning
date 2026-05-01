package com.airtribe.learning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.airtribe.learning.dto.TravelPackageRequestDTO;
import com.airtribe.learning.model.TravelPackage;

@Service
public class TravelPackageService {

    // In-memory list to store packages, replace with DB in real app
    List<TravelPackage> packages = new ArrayList<>();
    private Long idCounter = 1L;

    public TravelPackageService() {
        // In a real application, inject a repository here
        packages.add(new TravelPackage("Goa Trip",
                "Enjoy beaches and nightlife", 15000, "3D 2N", "Goa"));

        packages.add(new TravelPackage("Manali Trip",
                "Mountain adventure", 20000, "5D 4N", "Manali"));
    }

    public List<TravelPackage> getAllPackages() {
        return this.packages;
    }

    public Optional<TravelPackage> getPackageById(Long id) {
        return this.packages.stream()
                .filter(pkg -> pkg.getId().equals(id))
                .findFirst();
    }

    public TravelPackage addPackage(TravelPackageRequestDTO request) {
        TravelPackage travelPackage = new TravelPackage(
            request.getTitle(),
            request.getDescription(),
            request.getPrice(),
            request.getDuration(),
            request.getLocation()
        );
        travelPackage.setId(idCounter++);
        packages.add(travelPackage);
        return travelPackage;
    }

    public boolean deletePackageById(Long id) {
        return packages.removeIf(p -> p.getId().equals(id));
    }
}
