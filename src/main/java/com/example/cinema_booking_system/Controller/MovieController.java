package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.Model.Movie;
import com.example.cinema_booking_system.Model.Show;
import com.example.cinema_booking_system.Repositories.MovieRepository;
import com.example.cinema_booking_system.Services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/movie")
public class MovieController {
    private MovieServices movieServices;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieServices movieServices,
                           MovieRepository movieRepository) {
        this.movieServices = movieServices;
        this.movieRepository = movieRepository;
    }
    @GetMapping()
    public ResponseEntity<List<Movie>>getAll(){
        return new ResponseEntity<>(movieServices.getListMovie(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @CrossOrigin
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
    @GetMapping("/show/id={id}")
    public ResponseEntity<List<Show>> getListShow(@PathVariable long id){
        return  new ResponseEntity<>(movieServices.getListShowByMovieId(id),HttpStatus.OK);
    }
    @GetMapping("/isAdvertise")
    public ResponseEntity<List<Movie>>getListIsAdvertise(){
        return new ResponseEntity<>(movieServices.getListMovieIsAdvertise(),HttpStatus.OK);
    }
    @GetMapping("/nowShow")
    public ResponseEntity<List<Movie>>getListNowShow(){
        return new ResponseEntity<>(movieServices.getListNowShow(),HttpStatus.OK);
    }
}
