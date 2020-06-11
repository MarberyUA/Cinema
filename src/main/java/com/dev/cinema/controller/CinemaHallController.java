package com.dev.cinema.controller;

import com.dev.cinema.model.dto.request.CinemaHallRequestDto;
import com.dev.cinema.model.dto.response.CinemaHallResponseDto;
import com.dev.cinema.model.mapper.CinemaHallMapper;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private CinemaHallMapper cinemaHallMapper;

    @GetMapping("/")
    public List<CinemaHallResponseDto> all() {
        return cinemaHallService.getAll()
                .stream()
                .map(i -> cinemaHallMapper.cinemaHallToCinemaHallResponse(i))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallMapper
                .cinemaHallRequestDtoToCinemaHall(cinemaHallRequestDto));
    }
}
