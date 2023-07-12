package com.example.cinema_booking_system.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity(name = "token")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account account;
    @Column(nullable = false, unique = true)
    private String token;

    public String tokenType = "BEARER";

    public boolean revoked;

    public boolean expired;
}
