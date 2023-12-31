package com.example.cinema_booking_system.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
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
    private Date createOn;
    private Date startTime;
    private int duration;
    @ManyToOne
    @JoinColumn(name = "movie_id")
//            @JsonIgnore
    Movie movie;
    @ManyToOne
    @JoinColumn(name = "cinema_hall_id")
    private CinemaHall cinemaHall;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ShowSeat> showSeats;

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", createOn=" + createOn +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", movie=" + movie.getId() +
                ", cinemaHall=" + cinemaHall.getId() +
                ", showSeats=" + showSeats +
                '}';
    }
}
