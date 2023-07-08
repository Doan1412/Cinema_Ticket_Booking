package com.example.cinema_booking_system.Model;

import com.example.cinema_booking_system.DataType.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private CinemaHall cinemaHall;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Override
    public String toString() {
        return "CinemaHallSeat{" +
                "id=" + id +
                ", row=" + row +
                ", number=" + number +
                ", cinemaHall=" + cinemaHall.getId() +
                ", seatType=" + seatType +
                '}';
    }
}
