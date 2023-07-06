package com.example.cinema_booking_system.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
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
    String bannerUrl;
    String trailerUrl;
    String actorList;
    @Nullable
    boolean isAdvertise;
    @Nullable
    double rating;
    // Staff addBy
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Show> listShow;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duartionMin=" + duartionMin +
                ", language='" + language + '\'' +
                ", releaseDate=" + releaseDate +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", actorList='" + actorList + '\'' +
                ", isAdvertise=" + isAdvertise +
                ", rating=" + rating +
//                ", listShow=" + listShow +
                '}';
    }
}
