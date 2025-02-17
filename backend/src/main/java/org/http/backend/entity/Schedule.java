package org.http.backend.entity;

import org.http.backend.util.CinemaHall;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Schedule {
    @Id
    private String id;
    private LocalDateTime localDateTime;
    private Cinema cinema;
    private CinemaHall cinemaHall;
    private Movie movie;

    public Schedule() {
    }

    public Schedule(LocalDateTime localDateTime, Cinema cinema, CinemaHall cinemaHall, Movie movie) {
        this.localDateTime = localDateTime;
        this.cinema = cinema;
        this.cinemaHall = cinemaHall;
        this.movie = movie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
