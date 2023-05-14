package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.Model.Movie;
import com.example.cinema_booking_system.Services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {
    @Autowired
    private MovieServices movieServices;

    public MovieController(MovieServices movieServices) {
        this.movieServices = movieServices;
    }
    @GetMapping()
    public ResponseEntity<List<Movie>>getAll(){
        return new ResponseEntity<>(movieServices.getListMovie(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie>getMovieById(@PathVariable long id){
        return new ResponseEntity<>(movieServices.getMovieById(id),HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<Movie>createMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(movieServices.create(movie),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable long id){
        movieServices.delete(id);
        return;
    }
    @PatchMapping("/update")
    public ResponseEntity<Movie>updateMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(movieServices.update(movie),HttpStatus.OK);
    }
}
