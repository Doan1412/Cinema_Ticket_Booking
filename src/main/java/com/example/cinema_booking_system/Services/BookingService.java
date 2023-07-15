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
    private final AccountRepository accountRepository;

    @Autowired
    public BookingService(ShowRepository showRepository,CustomerRepository customerRepository,
                          BookingRepository bookingRepository,
                          PaymentRepository paymentRepository,
                          ShowSeatRepository showSeatRepository,
                          AccountRepository accountRepository) {
        this.showRepository = showRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.showSeatRepository = showSeatRepository;
        this.accountRepository = accountRepository;
    }
    public BookingDTO create(BookingDTO bookingDTO){
        Show show = showRepository.findById(bookingDTO.getShowId()).orElseThrow();
        Customer customer = customerRepository.findByAccountUsername(bookingDTO.getUsername()).orElseThrow();
//        System.out.println(customer);
        //Customer customer = customerRepository.findById(bookingDTO.getCustomerId()).orElseThrow();
        List<ShowSeat> seats = new ArrayList<>();
        for (Long seatId: bookingDTO.getListSeatId()) {
            ShowSeat seat = showSeatRepository.findById(seatId).orElseThrow();
            seats.add(seat);
            seat.setReserved(true);
            showSeatRepository.save(seat);
        }
        var booking = Booking.builder()
                .createOn(bookingDTO.getCreateOn())
                .status(BookingStatus.CONFIRMED)
                .show(show)
                .customer(customer)
                .showSeats(seats)
                .build();
        Booking b= bookingRepository.save(booking);
        List<Booking> lb= customer.getBookings();
        lb.add(b);
        customer.setBookings(lb);
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
        bookingDTO.setId(b.getId());
//        bookingDTO.setPaymentId(b.getPayment().getId());
//        b.setPayment(p);
        return bookingDTO;
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

    public List<BookingDTO> getListBookingByUsername(String username) {
        Customer customer = customerRepository.findByAccountUsername(username).orElseThrow();
        List<Booking> list = bookingRepository.findByCustomer(customer);
        List<BookingDTO> res = new ArrayList<>();
        for (Booking b: list) {
            List<Long> listSeatId = new ArrayList<>();
            for (ShowSeat showSeat : b.getShowSeats()){
                listSeatId.add(showSeat.getId());
            }
            var booking = BookingDTO.builder()
                    .showId(b.getShow().getId())
                    .status(b.getStatus())
                    .listSeatId(listSeatId)
                    .movie(b.getShow().getMovie().getId())
                    .build();
            res.add(booking);
        }
        return res;
    }
}
