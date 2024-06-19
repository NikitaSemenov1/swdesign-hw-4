package com.swdesign.course.hw4.ticket.mappers;

import com.swdesign.course.hw4.ticket.dto.TicketDto;
import com.swdesign.course.hw4.ticket.entities.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketEntityToTicketDtoMapper {
    @Mapping(target = "toStationId", source = "toStation.id")
    @Mapping(target = "fromStationId", source = "fromStation.id")
    TicketDto map(TicketEntity ticketEntity);
}
