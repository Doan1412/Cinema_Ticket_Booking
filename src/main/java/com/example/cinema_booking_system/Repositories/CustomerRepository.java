package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
