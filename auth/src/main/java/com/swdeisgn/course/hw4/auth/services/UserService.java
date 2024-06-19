package com.swdeisgn.course.hw4.auth.services;

import com.swdeisgn.course.hw4.auth.dto.RegisterDto;
import com.swdeisgn.course.hw4.auth.dto.SessionDto;
import com.swdeisgn.course.hw4.auth.dto.UserDto;

import java.util.Date;
import java.util.UUID;

public interface UserService {
    UserDto createUser(RegisterDto userDto);
    UserDto getUser(UUID id);
    UserDto getUserByEmail(String email);
    SessionDto createSession(UUID user_id, String token, Date expires);
}
