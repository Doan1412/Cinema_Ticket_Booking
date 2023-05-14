package com.example.cinema_booking_system.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    int duartionMin;
    String language;
    Date releaseDate;
    String country;
    String genre;
    String posterUrl;
    String trailerUrl;
    // Staff addBy
    @OneToMany(cascade = CascadeType.ALL)
    List<Show> listShow;

}
