package com.airtribe.learning.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_packages")
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
    private Double price;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotBlank(message = "Location is required")
    private String location;

    @OneToMany(mappedBy = "travelPackage")
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

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

    public String getTitle() { 
        return title; 
    }

    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getDescription() { 
        return description; 
    }

    public void setDescription(String description) { 
        this.description = description; 
    }

    public Double getPrice() { 
        return price; 
    }

    public void setPrice(Double price) { 
        this.price = price; 
    }

    public String getDuration() { 
        return duration; 
    }

    public void setDuration(String duration) { 
        this.duration = duration; 
    }

    public String getLocation() { 
        return location; 
    }

    public void setLocation(String location) { 
        this.location = location; 
    }
}
