package com.example.cinema_booking_system.Services;

import com.example.cinema_booking_system.Model.ShowSeat;
import com.example.cinema_booking_system.Repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowSeatServices {
    private final ShowSeatRepository showSeatRepository;
    @Autowired
    public ShowSeatServices(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public ShowSeat getById(long id){
        return showSeatRepository.findById(id).orElseThrow();
    }
}
