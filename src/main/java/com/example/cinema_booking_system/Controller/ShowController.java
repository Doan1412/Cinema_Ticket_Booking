package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.DTO.ShowDTO;
import com.example.cinema_booking_system.Model.*;
import com.example.cinema_booking_system.Services.ShowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/show")
public class ShowController {
    private final ShowServices showServices;

    @Autowired
    public ShowController(ShowServices showServices) {
        this.showServices = showServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Show>>  getall(){
        return new ResponseEntity<>(showServices.getList(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Show> create (@RequestBody ShowDTO showDTO){
        return new ResponseEntity<>(showServices.create(showDTO),HttpStatus.CREATED);
    }

    @GetMapping("/id={id}")
    @CrossOrigin
    public ResponseEntity<Show> getById(@PathVariable long id){
        return new ResponseEntity<>(showServices.getShowById(id),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Show> update(@RequestBody ShowDTO showDTO){
        return new ResponseEntity<>(showServices.update(showDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete/id={id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        showServices.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/movie={id}")
    public ResponseEntity<List<Show>> getByMovieId(@PathVariable long id){
        return new ResponseEntity<>(showServices.getByMovieId(id),HttpStatus.OK);
    }
}
