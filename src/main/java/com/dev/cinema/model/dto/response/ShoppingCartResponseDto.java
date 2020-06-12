package com.dev.cinema.model.dto.response;

import java.util.List;

public class ShoppingCartResponseDto {
    private List<TicketResponseDto> tickets;
    private String userName;

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
