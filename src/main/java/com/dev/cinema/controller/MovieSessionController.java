package com.dev.cinema.controller;

import com.dev.cinema.exceptions.SettingDateTimeException;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.request.MovieSessionRequestDto;
import com.dev.cinema.model.dto.response.MovieSessionResponseDto;
import com.dev.cinema.model.mapper.MovieSessionMapper;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    @Autowired
    private MovieSessionService movieSessionService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private MovieSessionMapper movieSessionMapper;

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = movieSessionMapper
                .movieSessionRequestDtoToMovieSession(movieSessionRequestDto);
        movieSession.setMovie(movieService.get(movieSession.getMovie().getId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSession.getCinemaHall().getId()));
        movieSessionService.add(movieSession);

    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSessions(@RequestParam("movieId") Long id,
                                                              @RequestParam("date") String date) {
        try {
            LocalDate showDate = LocalDate.parse(date);
            return movieSessionService.findAvailableSessions(id, showDate)
                    .stream()
                    .map(session -> movieSessionMapper
                            .movieSessionToMovieSessionResponseDto(session))
                    .collect(Collectors.toList());
        } catch (DateTimeParseException e) {
            throw new SettingDateTimeException("Could not parse date, "
                    + "check whatever entered data is valid.");
        }
    }

}
