package com.example.cinema_booking_system.DTO;

import com.example.cinema_booking_system.DataType.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Role role;
}
