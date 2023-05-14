package com.example.cinema_booking_system.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createOn;
    private LocalDateTime startTime;
    private int duration;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    Movie movie;
    @ManyToOne
    @JoinColumn(name = "cinema_hall_id")
    private CinemaHall cinemaHall;
    @OneToMany
    private List<ShowSeat> showSeats;
}
