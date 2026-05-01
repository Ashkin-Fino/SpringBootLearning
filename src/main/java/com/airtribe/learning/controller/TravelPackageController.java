package com.airtribe.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airtribe.learning.dto.TravelPackageRequestDTO;
import com.airtribe.learning.dto.TravelPackageResponseDTO;
import com.airtribe.learning.model.TravelPackage;
import com.airtribe.learning.service.TravelPackageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/packages")
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    @Autowired
    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @GetMapping
    public ResponseEntity<List<TravelPackageResponseDTO>> getPackages() {
        List<TravelPackageResponseDTO> response = travelPackageService.getAllPackages()
            .stream()
            .map(p -> new TravelPackageResponseDTO(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getPrice(),
                p.getDuration(),
                p.getLocation()
            )).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPackageResponseDTO> getPackageById(@PathVariable Long id) {
        return travelPackageService.getPackageById(id)
            .map(p -> new TravelPackageResponseDTO(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getDuration(),
                    p.getLocation()
            ))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TravelPackageResponseDTO> addPackage(@Valid @RequestBody TravelPackageRequestDTO travelPackageRequest) {
        TravelPackage createdTravelPackage = travelPackageService.addPackage(travelPackageRequest);
        TravelPackageResponseDTO response = new TravelPackageResponseDTO(
            createdTravelPackage.getId(),
            createdTravelPackage.getTitle(),
            createdTravelPackage.getDescription(),
            createdTravelPackage.getPrice(),
            createdTravelPackage.getDuration(),
            createdTravelPackage.getLocation()
        );
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {

        boolean deleted = travelPackageService.deletePackageById(id);

        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
