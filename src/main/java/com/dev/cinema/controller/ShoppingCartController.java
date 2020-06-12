package com.dev.cinema.controller;

import com.dev.cinema.model.dto.request.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.response.ShoppingCartResponseDto;
import com.dev.cinema.model.mapper.ShoppingCartMapper;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private MovieSessionService movieSessionService;

    @PostMapping("/add-movie-session")
    public void addMovieSessionToShoppingCart(
            @RequestBody ShoppingCartRequestDto shoppingCartRequestDto,
            @RequestParam Long userId) {

        shoppingCartService.addSession(movieSessionService.get(shoppingCartMapper
                .movieSessionIdFromShoppingCartRequestDto(shoppingCartRequestDto)),
                userService.get(userId));

    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getShoppingCartByUser(@RequestParam Long userId) {
        return shoppingCartMapper.shoppingCartToShoppingCartResponseDto(shoppingCartService
                .getByUser(userService.get(userId)));
    }
}
