package com.swdeisgn.course.hw4.auth.services;

import com.swdeisgn.course.hw4.auth.dto.RegisterDto;
import com.swdeisgn.course.hw4.auth.dto.SessionDto;
import com.swdeisgn.course.hw4.auth.dto.UserDto;
import com.swdeisgn.course.hw4.auth.entities.SessionEntity;
import com.swdeisgn.course.hw4.auth.entities.UserEntity;
import com.swdeisgn.course.hw4.auth.exceptions.ClientErrorException;
import com.swdeisgn.course.hw4.auth.exceptions.UserNotFoundException;
import com.swdeisgn.course.hw4.auth.mappers.RegisterDtoToUserEntityMapper;
import com.swdeisgn.course.hw4.auth.mappers.SessionEntityToSessionDtoMapper;
import com.swdeisgn.course.hw4.auth.mappers.UserEntityToUserDtoMapper;
import com.swdeisgn.course.hw4.auth.repositories.SessionRepository;
import com.swdeisgn.course.hw4.auth.repositories.UserRepository;
import com.swdeisgn.course.hw4.auth.security.JwtProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityToUserDtoMapper userEntityToUserDtoMapper;
    private final RegisterDtoToUserEntityMapper registerDtoToUserEntityMapper;
    private final SessionEntityToSessionDtoMapper sessionEntityToSessionDtoMapper;

    public UserServiceImpl(UserRepository userRepository, SessionRepository sessionRepository, PasswordEncoder passwordEncoder, UserEntityToUserDtoMapper userEntityToUserDtoMapper, RegisterDtoToUserEntityMapper registerDtoToUserEntityMapper, SessionEntityToSessionDtoMapper sessionEntityToSessionDtoMapper) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
        this.userEntityToUserDtoMapper = userEntityToUserDtoMapper;
        this.registerDtoToUserEntityMapper = registerDtoToUserEntityMapper;
        this.sessionEntityToSessionDtoMapper = sessionEntityToSessionDtoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // auth via email
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", email)));

        return new User(userEntity.getEmail(), userEntity.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
    }

    @Override
    public UserDto getUser(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return userEntityToUserDtoMapper.map(userEntity);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        return userEntityToUserDtoMapper.map(userEntity);
    }

    @Override
    public SessionDto createSession(UUID user_id, String token, Date expires) {
        UserEntity userEntity = userRepository.findById(user_id).orElseThrow(UserNotFoundException::new);

        SessionEntity sessionEntity = SessionEntity.builder()
                .user(userEntity)
                .token(token)
                .expires(new Timestamp(expires.getTime()))
                .build();

        sessionRepository.save(sessionEntity);

        return sessionEntityToSessionDtoMapper.map(sessionEntity);
    }

    @Override
    public UserDto createUser(RegisterDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()).isPresent())
            throw new ClientErrorException(String.format("User with username %s exists", registerDto.getUsername()));

        if (userRepository.findByEmail(registerDto.getEmail()).isPresent())
            throw new ClientErrorException(String.format("User with email %s exists", registerDto.getEmail()));

        UserEntity userEntity = registerDtoToUserEntityMapper.map(registerDto);
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userRepository.save(userEntity);

        return userEntityToUserDtoMapper.map(userEntity);
    }
}
