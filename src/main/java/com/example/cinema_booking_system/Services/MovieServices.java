package com.example.cinema_booking_system.Services;

import com.example.cinema_booking_system.Model.Movie;
import com.example.cinema_booking_system.Model.Show;
import com.example.cinema_booking_system.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServices {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieServices(MovieRepository movieRepositories) {
        this.movieRepository = movieRepositories;
    }
    public Movie create(Movie movie){
        return movieRepository.save(movie);
    }
    public void delete(long id){
        movieRepository.deleteById(id);
        return;
    }
    public Movie update(Movie movie){
        return movieRepository.save(movie);
    }
    public Movie getMovieById(long id){
        return movieRepository.findById(id).get();
    }
    public List<Movie> getListMovie(){
        return movieRepository.findAll();
    }
    public List<Show> getListShowByMovieId(long id){
        Movie movie = movieRepository.findById(id).orElseThrow();
        return movie.getListShow();
    }
}
