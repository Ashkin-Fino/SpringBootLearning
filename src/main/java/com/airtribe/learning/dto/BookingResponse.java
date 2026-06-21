package com.airtribe.learning.dto;

import com.airtribe.learning.entity.BookingStatus;

import java.time.LocalDate;

public class BookingResponse {

    private Long id;
    private Long userId;
    private String username;
    private Long travelPackageId;
    private String travelPackageTitle;
    private Integer numberOfPeople;
    private LocalDate bookingDate;
    private LocalDate travelDate;
    private BookingStatus status;
    private Double totalPrice;

    public BookingResponse() {
    }

    public BookingResponse(Long id,
                           Long userId,
                           String username,
                           Long travelPackageId,
                           String travelPackageTitle,
                           Integer numberOfPeople,
                           LocalDate bookingDate,
                           LocalDate travelDate,
                           BookingStatus status,
                           Double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.travelPackageId = travelPackageId;
        this.travelPackageTitle = travelPackageTitle;
        this.numberOfPeople = numberOfPeople;
        this.bookingDate = bookingDate;
        this.travelDate = travelDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Long getTravelPackageId() {
        return travelPackageId;
    }

    public String getTravelPackageTitle() {
        return travelPackageTitle;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
