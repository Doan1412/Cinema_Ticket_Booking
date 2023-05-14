package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
}