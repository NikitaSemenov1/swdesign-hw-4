package com.swdesign.course.hw4.ticket.repositories;

import com.swdesign.course.hw4.ticket.entities.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StationRepository extends JpaRepository<StationEntity, UUID> {
}
