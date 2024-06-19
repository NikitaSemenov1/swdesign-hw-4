package com.swdeisgn.course.hw4.auth.controllers;

import com.swdeisgn.course.hw4.auth.dto.LoginDto;
import com.swdeisgn.course.hw4.auth.dto.RegisterDto;
import com.swdeisgn.course.hw4.auth.dto.SessionDto;
import com.swdeisgn.course.hw4.auth.dto.UserDto;
import com.swdeisgn.course.hw4.auth.exceptions.UserNotFoundException;
import com.swdeisgn.course.hw4.auth.security.JwtProvider;
import com.swdeisgn.course.hw4.auth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "users")
public class UserController {
    UserService userService;
    AuthenticationManager authenticationManager;
    JwtProvider jwtProvider;

    UserController(UserService userService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/{id}")
    @ResponseBody
    UserDto getUser(@PathVariable("id") UUID id) {

        String token = (String) SecurityContextHolder.getContext().getAuthentication().getDetails();

        UUID user_id = UUID.fromString(jwtProvider.getUserIdFromToken(token));

        if (!user_id.equals(id)) {
            throw new UserNotFoundException();
        }

        return userService.getUser(id);
    }

    @PostMapping("/register")
    @ResponseBody
    UserDto registerUser(@Valid @RequestBody RegisterDto registerDto) {
        return userService.createUser(registerDto);
    }

    @PostMapping("/login")
    @ResponseBody
    SessionDto loginUser(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        authenticationManager.authenticate(authenticationToken);

        UserDto user = userService.getUserByEmail(loginDto.getEmail());
        String token = jwtProvider.generateToken(user.getId());

        authenticationToken.setDetails(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return userService.createSession(user.getId(), token, jwtProvider.getExpirationDateFromToken(token));
    }
}
