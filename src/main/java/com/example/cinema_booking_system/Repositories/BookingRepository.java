package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
