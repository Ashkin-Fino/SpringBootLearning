package com.airtribe.learning.service;

import com.airtribe.learning.dto.BookingRequest;
import com.airtribe.learning.dto.BookingResponse;
import com.airtribe.learning.entity.Booking;
import com.airtribe.learning.entity.BookingStatus;
import com.airtribe.learning.entity.TravelPackage;
import com.airtribe.learning.entity.User;
import com.airtribe.learning.exception.ResourceNotFoundException;
import com.airtribe.learning.repository.BookingRepository;
import com.airtribe.learning.repository.TravelPackageRepository;
import com.airtribe.learning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TravelPackageRepository travelPackageRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          TravelPackageRepository travelPackageRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.travelPackageRepository = travelPackageRepository;
        this.userRepository = userRepository;
    }

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::mapToBookingResponse).toList();
    }

    public BookingResponse createBooking(String username, BookingRequest request) {

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException(
                "User not found with username: " + username
            ));

        TravelPackage travelPackage = travelPackageRepository.findById(request.getTravelPackageId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Travel package not found with id: " + request.getTravelPackageId()
            ));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTravelPackage(travelPackage);
        booking.setNumberOfPeople(request.getNumberOfPeople());
        booking.setTravelDate(request.getTravelDate());
        booking.setBookingDate(LocalDate.now());
        booking.setStatus(BookingStatus.CONFIRMED);

        double totalPrice = travelPackage.getPrice() * request.getNumberOfPeople();
        booking.setTotalPrice(totalPrice);

        Booking savedBooking = bookingRepository.save(booking);
        return mapToBookingResponse(savedBooking);
    }

    public List<BookingResponse> getMyBookings(String username) {

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException(
                "User not found with username: " + username
            ));
        List<Booking> bookings = bookingRepository.findByUser(user);
        return bookings.stream().map(this::mapToBookingResponse).toList();
    }

    private BookingResponse mapToBookingResponse(Booking booking) {
        return new BookingResponse(
            booking.getId(),
            booking.getUser().getId(),
            booking.getUser().getUsername(),
            booking.getTravelPackage().getId(),
            booking.getTravelPackage().getTitle(),
            booking.getNumberOfPeople(),
            booking.getBookingDate(),
            booking.getTravelDate(),
            booking.getStatus(),
            booking.getTotalPrice()
        );
    }
}
