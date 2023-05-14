package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
