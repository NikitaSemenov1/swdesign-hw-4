package com.swdesign.course.hw4.ticket.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@Entity
@Table(name = "stations")
@NoArgsConstructor
@AllArgsConstructor
public class StationEntity {

    @Id
    private UUID id;

    private String name;
}


