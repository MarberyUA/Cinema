package com.dev.cinema.model.mapper;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.response.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto ticketToTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setTicketId(ticket.getId());
        ticketResponseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        return ticketResponseDto;
    }
}
