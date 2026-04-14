package com.airtribe.learning.dto;

import jakarta.validation.constraints.*;

public class TravelPackageRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotBlank(message = "Location is required")
    private String location;

    public TravelPackageRequestDTO() {
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }
}