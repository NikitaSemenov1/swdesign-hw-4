package com.swdesign.course.hw4.ticket.mappers;

import com.swdesign.course.hw4.ticket.entities.StationEntity;
import com.swdesign.course.hw4.ticket.entities.TicketEntity;
import com.swdesign.course.hw4.ticket.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketDtoToTicketEntityMapper {

    @Mapping(source = "ticketDto.id", target = "id")
    TicketEntity map(TicketDto ticketDto, StationEntity fromStation, StationEntity toStation);
}
