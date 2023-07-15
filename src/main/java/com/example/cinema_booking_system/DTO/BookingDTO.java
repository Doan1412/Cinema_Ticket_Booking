package com.example.cinema_booking_system.DTO;

import com.example.cinema_booking_system.DataType.BookingStatus;
import com.example.cinema_booking_system.Model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern =  "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createOn;
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CONFIRMED;
    private Long showId;

    private String username;

    private Long paymentId;

    private List<Long> listSeatId;
    private Long movie;
}
