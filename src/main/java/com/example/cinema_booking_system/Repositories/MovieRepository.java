package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Movie;
import com.example.cinema_booking_system.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByIsAdvertise(boolean t);
    List<Movie> findByListShowNotNull();
    List<Movie> findByListShow(Show show);
}
