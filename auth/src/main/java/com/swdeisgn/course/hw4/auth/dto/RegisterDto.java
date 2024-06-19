package com.swdeisgn.course.hw4.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @Null
    UUID id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Null
    private Timestamp created;
}

