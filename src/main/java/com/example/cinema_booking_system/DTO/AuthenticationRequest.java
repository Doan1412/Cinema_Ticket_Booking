package com.example.cinema_booking_system.DTO;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
