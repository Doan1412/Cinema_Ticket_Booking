package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Account;
import com.example.cinema_booking_system.Model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByAccount(Account account);
}
