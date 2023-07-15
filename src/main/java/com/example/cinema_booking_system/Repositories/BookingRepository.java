package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Account;
import com.example.cinema_booking_system.Model.Booking;
import com.example.cinema_booking_system.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByShowId(Long id);

    List<Booking> findByCustomerId(long id);
    List<Booking> findByCreateOnBetween(Date d1, Date d2);
    List<Booking> findByCustomer(Customer customer);
}
