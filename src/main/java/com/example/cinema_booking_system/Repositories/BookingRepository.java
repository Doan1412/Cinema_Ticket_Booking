package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByShowId(Long id);

    List<Booking> findByCustomerId(long id);
}
