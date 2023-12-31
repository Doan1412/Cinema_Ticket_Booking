package com.example.cinema_booking_system.Services;

import com.example.cinema_booking_system.DTO.ShowDTO;
import com.example.cinema_booking_system.DataType.SeatType;
import com.example.cinema_booking_system.Model.*;
import com.example.cinema_booking_system.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowServices {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final CinemaHallRepository cinemaHallRepository;
    private final CinemaHallSeatReposity cinemaHallSeatReposity;
    private final ShowSeatRepository showSeatRepository;

    @Autowired
    public ShowServices(ShowRepository showRepository, MovieRepository movieRepositories, CinemaHallRepository cinemaHallRepositories, CinemaHallSeatReposity cinemaHallSeatReposity,
                        ShowSeatRepository showSeatRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepositories;
        this.cinemaHallRepository = cinemaHallRepositories;
        this.cinemaHallSeatReposity = cinemaHallSeatReposity;
        this.showSeatRepository = showSeatRepository;
    }
    public List<Show> findOverlappingShows(CinemaHall cinemaHall, LocalDateTime startTime, LocalDateTime endTime) {
        List<Show> allShows = showRepository.findByCinemaHall(cinemaHall);
        List<Show> overlappingShows = new ArrayList<>();
        if (allShows.isEmpty()) return overlappingShows;
        for (Show show : allShows) {
            LocalDateTime showStartTime = show.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();;
            if (showStartTime == null) continue; // Skip iteration if showStartTime is null
            LocalDateTime showEndTime = showStartTime.plusMinutes(show.getMovie().getDuartionMin());

            if ((showStartTime.isAfter(startTime) && showStartTime.isBefore(endTime)) ||
                    (startTime.isAfter(showStartTime) && startTime.isBefore(showEndTime))) {
                overlappingShows.add(show);
            }
        }
        return overlappingShows;
    }
    public boolean isShowOverlap(CinemaHall cinemaHall, LocalDateTime startTime, LocalDateTime endTime) {
        List<Show> overlappingShows = findOverlappingShows(cinemaHall, startTime, endTime);
        return !overlappingShows.isEmpty();
    }
    public List<Show>getList(){
        return showRepository.findAll();
    }
    @Transactional
    public Show create(ShowDTO showDTO){
        Movie movie = movieRepository.findById(showDTO.getMovieId()).orElseThrow();
        CinemaHall cinemaHall = cinemaHallRepository.findById(showDTO.getCinemaHallId()).orElseThrow();
        if (!isShowOverlap(cinemaHall,showDTO.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),showDTO.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMinutes(movie.getDuartionMin()))){
            var show = Show.builder()
                    .startTime(showDTO.getStartTime())
                    .createOn(showDTO.getCreateOn())
                    .duration(showDTO.getDuration())
                    .movie(movie)
                    .cinemaHall(cinemaHall)
                    .build();
            Show s = showRepository.save(show);
            movie.getListShow().add(s);
            List<CinemaHallSeat> cinemaHallSeats = cinemaHallSeatReposity.findByCinemaHallId(showDTO.getCinemaHallId());
            List<ShowSeat> showSeats = new ArrayList<>();
            for (CinemaHallSeat cinemaHallSeat:cinemaHallSeats) {
                for (int i=0;i<showDTO.getSeatTypes().size();i++) {
                    SeatType type = showDTO.getSeatTypes().get(i);
                    if(cinemaHallSeat.getSeatType() == type){
                        ShowSeat showSeat = new ShowSeat(showDTO.getSeatPrices().get(i),false,cinemaHallSeat,s);
                        showSeatRepository.save(showSeat);
                        showSeats.add(showSeat);
                    }
                }
            }
            s.setShowSeats(showSeats);
            return showRepository.save(s);
        }

        return null;
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id).orElseThrow();
    }
    public void delete(long id){
        Show s = showRepository.findById(id).orElseThrow();
        List<Movie> movies = movieRepository.findByListShow(s);
        for (Movie m:
             movies) {
            List<Show> shows= m.getListShow();
            shows.remove(s);
            m.setListShow(shows);
            movieRepository.save(m);
        }
        showRepository.deleteById(id);
        return;
    }
    public Show update(ShowDTO showDTO){
        Movie movie = movieRepository.findById(showDTO.getMovieId()).orElseThrow();
        CinemaHall cinemaHall = cinemaHallRepository.findById(showDTO.getCinemaHallId()).orElseThrow();
        var show = Show.builder()
                .id(showDTO.getId())
                .createOn(showDTO.getCreateOn())
                .duration(showDTO.getDuration())
                .movie(movie)
                .cinemaHall(cinemaHall)
                .build();
        Show s = showRepository.save(show);
        movie.getListShow().add(s);
        return s;
    }
    public List<Show> getByMovieId(long id){
        return showRepository.findByMovieId(id);
    }
}
