package com.airtribe.learning.controller;

import com.airtribe.learning.dto.BookingRequest;
import com.airtribe.learning.dto.BookingResponse;
import com.airtribe.learning.service.BookingService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request,
                                                         Authentication authentication) {

        String username = authentication.getName();
        BookingResponse response = bookingService.createBooking(username, request);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/my")
    public ResponseEntity<List<BookingResponse>> getMyBookings(Authentication authentication) {

        String username = authentication.getName();
        List<BookingResponse> response = bookingService.getMyBookings(username);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookingResponse> response = bookingService.getAllBookings();
        return ResponseEntity.ok(response);
    }
}
