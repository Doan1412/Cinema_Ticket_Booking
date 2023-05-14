package com.example.cinema_booking_system.DTO;

import com.example.cinema_booking_system.DataType.SeatType;
import com.example.cinema_booking_system.Model.CinemaHall;
import com.example.cinema_booking_system.Model.Movie;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShowDTO {
    Long id;
    LocalDateTime createOn;
    private LocalDateTime startTime;
    private int duration;
    Long movieId;
    private Long cinemaHallId;
    List<SeatType> seatTypes;
    List<Double> seatPrices;
}
