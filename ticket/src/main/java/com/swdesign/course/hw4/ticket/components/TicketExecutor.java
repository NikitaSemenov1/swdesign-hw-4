package com.swdesign.course.hw4.ticket.components;

import com.swdesign.course.hw4.ticket.dto.TicketDto;
import com.swdesign.course.hw4.ticket.entities.TicketEntity;
import com.swdesign.course.hw4.ticket.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TicketExecutor {
    private static final Logger log = LoggerFactory.getLogger(TicketExecutor.class);
    TicketRepository ticketRepository;

    RandomStatusProvider randomStatusProvider = new RandomStatusProvider();

    TicketExecutor(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void executeTicket() {
        Optional<TicketEntity> ticket = ticketRepository.findByStatus(TicketDto.Status.CHECK.name());

        log.info("ticket execution iteration");

        ticket.ifPresent(
                ticketEntity -> {
                    ticketEntity.setStatus(randomStatusProvider.getRandomStatus().name());
                    log.info("set status: {} to ticket {}", ticketEntity.getStatus(), ticketEntity.getId());
                }
        );
    }
}
