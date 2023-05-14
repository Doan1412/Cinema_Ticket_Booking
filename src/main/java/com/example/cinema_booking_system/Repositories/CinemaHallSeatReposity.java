package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.CinemaHallSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaHallSeatReposity extends JpaRepository<CinemaHallSeat,Long> {
    List<CinemaHallSeat> findByCinemaHallId(Long id);
}
