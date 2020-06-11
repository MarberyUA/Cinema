package com.dev.cinema.model.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.request.OrderRequestDto;
import com.dev.cinema.model.dto.response.OrderResponseDto;
import com.dev.cinema.model.dto.response.TicketResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    @Autowired
    private TicketMapper ticketMapper;

    public Long orderRequestDtoToUserId(OrderRequestDto orderRequestDto) {
        return orderRequestDto.getUserId();
    }

    public OrderResponseDto orderToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        List<TicketResponseDto> tickets = new ArrayList<>();
        for (Ticket ticket : order.getTickets()) {
            tickets.add(ticketMapper.ticketToTicketResponseDto(ticket));
        }
        orderResponseDto.setTickets(tickets);
        orderResponseDto.setUserName(order.getUser().getName());
        return orderResponseDto;
    }
}
