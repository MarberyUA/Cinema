package com.dev.cinema.model.dto.response;

import java.util.List;

public class OrderResponseDto {
    private String userName;
    private List<TicketResponseDto> tickets;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }
}
