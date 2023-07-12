package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.Model.CinemaHall;
import com.example.cinema_booking_system.Services.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/cinemaHall")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<CinemaHall>> getAll(){
        return new ResponseEntity<>(cinemaHallService.getList(), HttpStatus.OK);
    }

    @GetMapping("/id={id}")
    @CrossOrigin
    public ResponseEntity<CinemaHall> get(@PathVariable long id){
        return new ResponseEntity<>(cinemaHallService.getById(id),HttpStatus.OK);
    }
    @PostMapping("/new")
    @CrossOrigin
    public ResponseEntity<CinemaHall> create(@RequestBody CinemaHall cinemaHall){
        return new ResponseEntity<>(cinemaHallService.create(cinemaHall),HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    @CrossOrigin
    public ResponseEntity<CinemaHall> update(@RequestBody long id, String name){
        return new ResponseEntity<>(cinemaHallService.updateName(id,name),HttpStatus.OK);
    }

    @DeleteMapping("/delete/id={id}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable long id){
        cinemaHallService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
