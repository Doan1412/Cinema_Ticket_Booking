package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.Model.ShowSeat;
import com.example.cinema_booking_system.Services.ShowSeatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/showseat")
public class ShowSeatController {
    private final ShowSeatServices showSeatServices;
    @Autowired
    public ShowSeatController(ShowSeatServices showSeatServices) {
        this.showSeatServices = showSeatServices;
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<ShowSeat> getById(@PathVariable long id){
        return new ResponseEntity<>(showSeatServices.getById(id), HttpStatus.OK);
    }
}
