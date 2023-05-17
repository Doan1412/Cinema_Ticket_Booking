package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.DTO.BookingDTO;
import com.example.cinema_booking_system.Model.Booking;
import com.example.cinema_booking_system.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/booking")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
        return new ResponseEntity<>(bookingService.create(bookingDTO), HttpStatus.CREATED);
    }

    @GetMapping("/showId={id}")
    public ResponseEntity<List<Booking>> getListBookingByShowId(@PathVariable long id){
        return new ResponseEntity<>(bookingService.getListBookingByShowId(id),HttpStatus.OK);
    }
    @GetMapping("/customerId={id}")
    public ResponseEntity<List<Booking>> getListBookingByCustomerId(@PathVariable long id){
        return new ResponseEntity<>(bookingService.getListBookingByCustomer(id),HttpStatus.OK);
    }
    @GetMapping("/Id={id}")
    public ResponseEntity<Booking> getById(@PathVariable long id){
        return new ResponseEntity<>(bookingService.getById(id),HttpStatus.OK);
    }

}
