package com.example.cinema_booking_system.Model;

import com.example.cinema_booking_system.DataType.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHallSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int row;
    private int number;
    @ManyToOne
    private CinemaHall cinemaHall;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
