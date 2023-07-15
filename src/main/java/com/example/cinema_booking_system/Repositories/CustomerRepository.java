package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByAccountUsername(String usernam);
}
