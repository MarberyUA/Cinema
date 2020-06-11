package com.dev.cinema.controller;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.request.MovieRequestDto;
import com.dev.cinema.model.dto.response.MovieResponseDto;
import com.dev.cinema.model.mapper.MovieMapper;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/movies")
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    @GetMapping("/")
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(i -> makeMovieResponse(i))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void add(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.movieRequestDtoToMovie(movieRequestDto));
    }

    private MovieResponseDto makeMovieResponse(Movie movie) {
        return movieMapper.movieToMovieResponseDto(movie);
    }
}
