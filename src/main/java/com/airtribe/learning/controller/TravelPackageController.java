package com.airtribe.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String location,
            Pageable pageable) {

        Page<TravelPackage> page = this.travelPackageService.getAllPackages(location, pageable);

        List<TravelPackageResponseDTO> content = page.getContent()
            .stream()
            .map(p -> new TravelPackageResponseDTO(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getDuration(),
                    p.getLocation()
            ))
            .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("content", content);
        result.put("page", page.getNumber());
        result.put("size", page.getSize());
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPackageResponseDTO> getPackageById(@PathVariable Long id) {
        TravelPackage p = travelPackageService.getPackageById(id);
        TravelPackageResponseDTO response = new TravelPackageResponseDTO(
            p.getId(),
            p.getTitle(),
            p.getDescription(),
            p.getPrice(),
            p.getDuration(),
            p.getLocation()
        );
        return ResponseEntity.status(200).body(response); // 200 OK
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
        return ResponseEntity.status(201).body(response); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelPackageResponseDTO> updatePackage(@PathVariable Long id, @Valid @RequestBody TravelPackageRequestDTO request) {

        TravelPackage updated = travelPackageService.updatePackage(id, request);

        TravelPackageResponseDTO response = new TravelPackageResponseDTO(
            updated.getId(),
            updated.getTitle(),
            updated.getDescription(),
            updated.getPrice(),
            updated.getDuration(),
            updated.getLocation()
        );

        return ResponseEntity.status(200).body(response); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {

        travelPackageService.deletePackageById(id);

        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
