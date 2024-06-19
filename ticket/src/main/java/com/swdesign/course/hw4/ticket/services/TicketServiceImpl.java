package com.swdesign.course.hw4.ticket.services;

import com.swdesign.course.hw4.ticket.dto.TicketDto;
import com.swdesign.course.hw4.ticket.entities.StationEntity;
import com.swdesign.course.hw4.ticket.entities.TicketEntity;
import com.swdesign.course.hw4.ticket.exceptions.EntityNotFoundException;
import com.swdesign.course.hw4.ticket.mappers.TicketDtoToTicketEntityMapper;
import com.swdesign.course.hw4.ticket.repositories.TicketRepository;
import com.swdesign.course.hw4.ticket.exceptions.ClientErrorException;
import com.swdesign.course.hw4.ticket.mappers.TicketEntityToTicketDtoMapper;
import com.swdesign.course.hw4.ticket.repositories.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
    private final TicketEntityToTicketDtoMapper ticketEntityToTicketDtoMapper;
    private final TicketDtoToTicketEntityMapper ticketDtoToTicketEntityMapper;
    private final TicketRepository ticketRepository;
    private final StationRepository stationRepository;

    TicketServiceImpl(TicketRepository ticketRepository, TicketEntityToTicketDtoMapper ticketEntityToTicketDtoMapper, TicketDtoToTicketEntityMapper ticketDtoToTicketEntityMapper, StationRepository stationRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketEntityToTicketDtoMapper = ticketEntityToTicketDtoMapper;
        this.ticketDtoToTicketEntityMapper = ticketDtoToTicketEntityMapper;
        this.stationRepository = stationRepository;
    }

    @Override
    public TicketDto findTicketById(UUID id) {
        TicketEntity ticketEntity = ticketRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );

        return ticketEntityToTicketDtoMapper.map(ticketEntity);
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {

        StationEntity fromStationEntity = stationRepository.findById(ticketDto.getFromStationId()).orElseThrow(
                () -> new ClientErrorException("station with such id is not exists, id=" + ticketDto.getFromStationId())
        );

        StationEntity toStationEntity = stationRepository.findById(ticketDto.getToStationId()).orElseThrow(
                () -> new ClientErrorException("station with such id is not exists, id=" + ticketDto.getToStationId())
        );

        if (fromStationEntity.getId() == toStationEntity.getId())
            throw new ClientErrorException("fromStation and toStation are the same");

        TicketEntity ticketEntity = ticketDtoToTicketEntityMapper.map(ticketDto, fromStationEntity, toStationEntity);

        ticketEntity.setStatus(TicketDto.Status.CHECK.name());

        ticketRepository.save(ticketEntity);


        return ticketEntityToTicketDtoMapper.map(ticketEntity);
    }
}
