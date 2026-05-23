package com.airtribe.learning.dto;

public class TravelPackageResponse {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String duration;
    private String location;

    public TravelPackageResponse(Long id, String title, String description,
                                 double price, String duration, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.location = location;
    }

    // Getters
    public Long getId() { 
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
