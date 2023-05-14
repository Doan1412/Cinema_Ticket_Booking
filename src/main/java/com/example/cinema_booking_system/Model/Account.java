package com.example.cinema_booking_system.Model;

import com.example.cinema_booking_system.DataType.AccountStatus;
import com.example.cinema_booking_system.DataType.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToOne
    private Person person;
    private AccountStatus status;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
