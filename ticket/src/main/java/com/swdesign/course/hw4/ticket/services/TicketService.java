package com.swdesign.course.hw4.ticket.services;

import com.swdesign.course.hw4.ticket.dto.TicketDto;

import java.util.UUID;

public interface TicketService {
    TicketDto findTicketById(UUID id);

    TicketDto createTicket(TicketDto ticketDto);
}