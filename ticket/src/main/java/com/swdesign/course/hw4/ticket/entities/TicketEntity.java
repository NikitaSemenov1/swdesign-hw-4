package com.swdesign.course.hw4.ticket.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.sql.Timestamp;
import java.util.UUID;


@Data
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;  // not foreign_key

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_station_id")
    private StationEntity fromStation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_station_id")
    private StationEntity toStation;

    @CreationTimestamp(source = SourceType.DB)
    private Timestamp created;
}
