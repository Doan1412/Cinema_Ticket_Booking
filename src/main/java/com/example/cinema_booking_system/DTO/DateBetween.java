package com.example.cinema_booking_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
@Data
public class DateBetween {
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endDate;
}
