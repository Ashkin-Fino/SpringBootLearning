package com.airtribe.learning.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingRequest {

    @NotNull(message = "travelPackageId is required")
    private Long travelPackageId;

    @NotNull(message = "numberOfPeople is required")
    @Min(value = 1, message = "numberOfPeople must be at least 1")
    private Integer numberOfPeople;

    @NotNull(message = "travelDate is required")
    @Future(message = "travelDate must be a future date")
    private LocalDate travelDate;

    public BookingRequest() {
    }

    public BookingRequest(Long travelPackageId, Integer numberOfPeople, LocalDate travelDate) {
        this.travelPackageId = travelPackageId;
        this.numberOfPeople = numberOfPeople;
        this.travelDate = travelDate;
    }

    public Long getTravelPackageId() {
        return travelPackageId;
    }

    public void setTravelPackageId(Long travelPackageId) {
        this.travelPackageId = travelPackageId;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }
}
