package com.example.cinema_booking_system.Services;

import com.example.cinema_booking_system.DTO.BookingDTO;
import com.example.cinema_booking_system.DataType.BookingStatus;
import com.example.cinema_booking_system.DataType.PaymentStatus;
import com.example.cinema_booking_system.Model.*;
import com.example.cinema_booking_system.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    private final ShowRepository showRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final ShowSeatRepository showSeatRepository;

    @Autowired
    public BookingService(ShowRepository showRepository,CustomerRepository customerRepository,
                          BookingRepository bookingRepository,
                          PaymentRepository paymentRepository,
                          ShowSeatRepository showSeatRepository) {
        this.showRepository = showRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.showSeatRepository = showSeatRepository;
    }
    public Booking create(BookingDTO bookingDTO){
        Show show = showRepository.findById(bookingDTO.getShowId()).orElseThrow();
        Customer customer = customerRepository.findById(bookingDTO.getCustomerId()).orElseThrow();
        List<ShowSeat> seats = new ArrayList<>();
        for (Long seatId: bookingDTO.getListSeatId()) {
            ShowSeat seat = showSeatRepository.findById(seatId).orElseThrow();
            seats.add(seat);
        }
        var booking = Booking.builder()
                .createOn(bookingDTO.getCreateOn())
                .status(BookingStatus.CONFIRMED)
                .show(show)
                .customer(customer)
                .showSeats(seats)
                .build();
        Booking b= bookingRepository.save(booking);
        double a=0;
        for (ShowSeat showSeat: b.getShowSeats()) {
            a+=showSeat.getPrice();
        }
        var payment = Payment.builder()
                .paymentDate(bookingDTO.getCreateOn())
                .paymentStatus(PaymentStatus.Pending)
                .amount(a)
                .booking(b)
                .build();
        Payment p = paymentRepository.save(payment);
        b.setPayment(p);
        return bookingRepository.save(b);
    }
    public Booking getById(long id){
        return bookingRepository.findById(id).orElseThrow();
    }
    public List<Booking> getListBookingByShowId(long id){
        return bookingRepository.findByShowId(id);
    }

    public List<Booking> getListBookingByCustomer(long id){
        return bookingRepository.findByCustomerId(id);
    }
    public List<Booking> getListBookingBetween(Date d1, Date d2){
        return bookingRepository.findByCreateOnBetween(d1,d2);
    }
    public void deteleBooking(long id){
        bookingRepository.deleteById(id);
    }
}
