package com.example.cinema_booking_system.Services;

import com.example.cinema_booking_system.DTO.BookingDTO;
import com.example.cinema_booking_system.DataType.BookingStatus;
import com.example.cinema_booking_system.DataType.PaymentStatus;
import com.example.cinema_booking_system.Model.*;
import com.example.cinema_booking_system.Repositories.BookingRepository;
import com.example.cinema_booking_system.Repositories.CustomerRepository;
import com.example.cinema_booking_system.Repositories.PaymentRepository;
import com.example.cinema_booking_system.Repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    private final ShowRepository showRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public BookingService(ShowRepository showRepository,CustomerRepository customerRepository,
                          BookingRepository bookingRepository,
                          PaymentRepository paymentRepository) {
        this.showRepository = showRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }
    public Booking create(BookingDTO bookingDTO){
        Show show = showRepository.findById(bookingDTO.getShowId()).orElseThrow();
        Customer customer = customerRepository.findById(bookingDTO.getCustomerId()).orElseThrow();
        var booking = Booking.builder()
                .createOn(bookingDTO.getCreateOn())
                .status(BookingStatus.CONFIRMED)
                .show(show)
                .customer(customer)
                .showSeats(show.getShowSeats())
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
