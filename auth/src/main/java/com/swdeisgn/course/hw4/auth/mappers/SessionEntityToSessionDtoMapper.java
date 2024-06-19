package com.swdeisgn.course.hw4.auth.mappers;

import com.swdeisgn.course.hw4.auth.dto.SessionDto;
import com.swdeisgn.course.hw4.auth.entities.SessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessionEntityToSessionDtoMapper {
    @Mapping(target = "user_id", source = "user.id")
    SessionDto map(SessionEntity sessionEntity);
}
