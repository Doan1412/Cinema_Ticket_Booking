package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.CinemaHall;
import com.example.cinema_booking_system.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

    List<Show> findByCinemaHall(CinemaHall cinemaHall);
    List<Show> findByMovieId(long id);
}
