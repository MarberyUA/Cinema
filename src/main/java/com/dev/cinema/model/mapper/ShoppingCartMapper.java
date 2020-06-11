package com.dev.cinema.model.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.request.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.response.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.response.TicketResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    @Autowired
    private TicketMapper ticketMapper;

    public ShoppingCartResponseDto shoppingCartToShoppingCartResponseDto(
            ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        List<TicketResponseDto> tickets = new ArrayList<>();
        for (Ticket ticket : shoppingCart.getSessions()) {
            tickets.add(ticketMapper.ticketToTicketResponseDto(ticket));
        }
        shoppingCartResponseDto.setTickets(tickets);
        shoppingCartResponseDto.setUserName(shoppingCart.getUser().getName());
        return shoppingCartResponseDto;
    }

    public Long movieSessionIdFromShoppingCartRequestDto(
            ShoppingCartRequestDto shoppingCartRequestDto) {
        return shoppingCartRequestDto.getMovieSessionId();
    }
}
