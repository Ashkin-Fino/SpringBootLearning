package com.airtribe.learning.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import jakarta.persistence.*;

@Entity
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be greater than 0")
    @NotNull(message = "Price is required")
    private double price;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotBlank(message = "Location is required")
    private String location;

    public TravelPackage() {
    }

    public TravelPackage(String title, String description,
                         double price, String duration, String location) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.location = location;
    }

    // Getters and setters
    public Long getId() { 
        return id; 
    }

    public void setId(long id) { 
        this.id = id; 
    }

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
