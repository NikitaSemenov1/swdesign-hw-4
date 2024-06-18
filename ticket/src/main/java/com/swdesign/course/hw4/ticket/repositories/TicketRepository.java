package com.swdesign.course.hw4.ticket.repositories;

import com.swdesign.course.hw4.ticket.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    Optional<TicketEntity> findByStatus(String status);
}
