package com.swdesign.course.hw4.ticket.controllers;

import com.swdesign.course.hw4.ticket.dto.TicketDto;
import com.swdesign.course.hw4.ticket.exceptions.EntityNotFoundException;
import com.swdesign.course.hw4.ticket.security.JwtProvider;
import com.swdesign.course.hw4.ticket.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/tickets")
public class        TicketController {

    TicketService ticketService;
    JwtProvider jwtProvider;

    TicketController(TicketService ticketService, JwtProvider jwtProvider) {
        this.ticketService = ticketService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public TicketDto getTicket(@PathVariable("id") UUID id) {
        TicketDto ticket =  ticketService.findTicketById(id);

        String token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
        UUID user_id = UUID.fromString(jwtProvider.getUserIdFromToken(token));

        if (!user_id.equals(ticket.getUserId())) {
            throw new EntityNotFoundException();
        }

        return ticket;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto createTicket(@Valid @RequestBody TicketDto ticketDto) {

        String token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
        UUID user_id = UUID.fromString(jwtProvider.getUserIdFromToken(token));

        ticketDto.setUserId(user_id);

        return ticketService.createTicket(ticketDto);
    }
}
