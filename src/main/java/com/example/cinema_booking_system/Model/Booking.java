package com.example.cinema_booking_system.Model;

import com.example.cinema_booking_system.DataType.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createOn;
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CONFIRMED;
    @OneToOne
    private Show show;
    @ManyToOne
    private Customer customer;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;
    @OneToMany
    private List<ShowSeat> showSeats;
}

