package com.swdeisgn.course.hw4.auth.mappers;

import com.swdeisgn.course.hw4.auth.dto.RegisterDto;
import com.swdeisgn.course.hw4.auth.dto.UserDto;
import com.swdeisgn.course.hw4.auth.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegisterDtoToUserEntityMapper {
    UserEntity map(RegisterDto registerDto);
}
