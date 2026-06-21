package com.airtribe.learning.repository;

import com.airtribe.learning.entity.Booking;
import com.airtribe.learning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);
}