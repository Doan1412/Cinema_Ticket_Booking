package com.example.cinema_booking_system.Services;

import com.example.cinema_booking_system.Model.CinemaHall;
import com.example.cinema_booking_system.Model.CinemaHallSeat;
import com.example.cinema_booking_system.Repositories.CinemaHallRepository;
import com.example.cinema_booking_system.Repositories.CinemaHallSeatReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaHallService {
    private final CinemaHallRepository cinemaHallRepository;
    private final CinemaHallSeatReposity cinemaHallSeatReposity;

    @Autowired
    public CinemaHallService(CinemaHallRepository cinemaHallRepository,
                             CinemaHallSeatReposity cinemaHallSeatReposity) {
        this.cinemaHallRepository = cinemaHallRepository;
        this.cinemaHallSeatReposity = cinemaHallSeatReposity;
    }

    public CinemaHall create(CinemaHall cinemaHallDto){
        var c= CinemaHall.builder()
                .name(cinemaHallDto.getName())
                .capacity(cinemaHallDto.getCapacity())
                .build();
        CinemaHall cinemaHall= cinemaHallRepository.save(c);
        List<CinemaHallSeat> list =new ArrayList<>();
        for (CinemaHallSeat chs: cinemaHallDto.getCinemaHallSeat()) {
            var cinemaHallSeat = CinemaHallSeat.builder()
                    .cinemaHall(cinemaHall)
                    .row(chs.getRow())
                    .number(chs.getNumber())
                    .seatType(chs.getSeatType())
                    .build();
            list.add(cinemaHallSeatReposity.save(cinemaHallSeat));
        }
        cinemaHall.setCinemaHallSeat(list);
        return cinemaHallRepository.save(cinemaHall);
    }
    public CinemaHall updateName(long id,String name){
        CinemaHall cinemaHall = cinemaHallRepository.findById(id).orElseThrow();
        cinemaHall.setName(name);
        return cinemaHallRepository.save(cinemaHall);
    }

    public void delete(long id){
        cinemaHallRepository.deleteById(id);
    }

    public CinemaHall getById(long id){
        return cinemaHallRepository.findById(id).orElseThrow();
    }

    public List<CinemaHall> getList(){
        return cinemaHallRepository.findAll();
    }
}
