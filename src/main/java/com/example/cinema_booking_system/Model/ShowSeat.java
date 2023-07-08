package com.example.cinema_booking_system.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
//    int seatNumber;
    boolean isReserved;
    double price;
    @OneToOne
    @JoinColumn(name = "cinema_hall_seat_id")
    private CinemaHallSeat cinemaHallSeat;
    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonIgnore
    private Show show;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private Booking booking;
    public ShowSeat(){

    }

    public ShowSeat(double price,boolean isReserved,  CinemaHallSeat cinemaHallSeat, Show show) {
        this.isReserved = isReserved;
        this.price = price;
        this.cinemaHallSeat = cinemaHallSeat;
        this.show = show;
    }

    @Override
    public String toString() {
        return "ShowSeat{" +
                "id=" + id +
                ", isReserved=" + isReserved +
                ", price=" + price +
                ", cinemaHallSeat=" + cinemaHallSeat +
                ", show=" + show.getId() +
                ", booking=" + booking.getId() +
                '}';
    }
}
