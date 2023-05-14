package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}