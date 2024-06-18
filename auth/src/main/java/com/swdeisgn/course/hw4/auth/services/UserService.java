package com.swdeisgn.course.hw4.auth.services;

import com.swdeisgn.course.hw4.auth.dto.RegisterDto;
import com.swdeisgn.course.hw4.auth.dto.SessionDto;
import com.swdeisgn.course.hw4.auth.dto.UserDto;

import java.sql.Timestamp;
import java.util.UUID;

public interface UserService {
    public UserDto createUser(RegisterDto userDto);
    public UserDto getUser(UUID id);
    public SessionDto createSession(String email);
}
