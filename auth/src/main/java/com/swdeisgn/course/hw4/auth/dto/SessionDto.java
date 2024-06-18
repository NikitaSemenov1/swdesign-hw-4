package com.swdeisgn.course.hw4.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {
    String user_id;
    String token;
    Timestamp expires;
}
