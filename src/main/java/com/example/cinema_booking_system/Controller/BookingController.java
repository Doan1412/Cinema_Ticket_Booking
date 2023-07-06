package com.example.cinema_booking_system.Controller;

import com.example.cinema_booking_system.DTO.BookingDTO;
import com.example.cinema_booking_system.DTO.DateBetween;
import com.example.cinema_booking_system.Model.Booking;
import com.example.cinema_booking_system.Repositories.BookingRepository;
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
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingService bookingService,
                             BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
        return new ResponseEntity<>(bookingService.create(bookingDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteBooking(@PathVariable long id){
        bookingService.deteleBooking(id);
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
    @GetMapping("/report/date")
    public ResponseEntity<List<Booking>> getByDate(@RequestBody DateBetween dateBetween){
        return new ResponseEntity<>(bookingRepository.findByCreateOnBetween(dateBetween.getStartDate(),dateBetween.getEndDate()),HttpStatus.OK);
    }

}
