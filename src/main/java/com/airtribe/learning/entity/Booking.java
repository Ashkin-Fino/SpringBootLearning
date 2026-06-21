package com.airtribe.learning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 1, message = "Number of people must be at least 1")
    private Integer numberOfPeople;

    @NotNull
    private LocalDate bookingDate;

    @NotNull
    @FutureOrPresent(message = "Travel date cannot be in the past")
    private LocalDate travelDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BookingStatus status;

    @NotNull
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_package_id", nullable = false)
    private TravelPackage travelPackage;

    public Booking() {
    }

    public Booking(Integer numberOfPeople, LocalDate bookingDate, LocalDate travelDate,
                   BookingStatus status, Double totalPrice, User user, TravelPackage travelPackage) {
        this.numberOfPeople = numberOfPeople;
        this.bookingDate = bookingDate;
        this.travelDate = travelDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.user = user;
        this.travelPackage = travelPackage;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }
}
