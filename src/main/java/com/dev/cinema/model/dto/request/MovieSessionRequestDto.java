package com.dev.cinema.model.dto.request;

import com.dev.cinema.exceptions.SettingDateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String showDate;
    private String showTime;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public LocalDateTime getShowDateTimeInDateTimeFormat() {
        try {
            return LocalDateTime.of(LocalDate.parse(showDate), LocalTime.parse(showTime));
        } catch (DateTimeParseException e) {
            throw new SettingDateTimeException("Could not parse date, "
                    + "check whatever entered data is valid.");
        }
    }

}
