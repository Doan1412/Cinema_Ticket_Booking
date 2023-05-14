package com.example.cinema_booking_system.DTO;

import com.example.cinema_booking_system.DataType.BookingStatus;
import com.example.cinema_booking_system.Model.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {
    private Long id;
    private LocalDateTime createOn;
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CONFIRMED;
    private Long showId;

    private Long customerId;

    //private Long paymentId;

    private List<CinemaHallSeat> seats;
}
