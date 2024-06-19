package com.swdesign.course.hw4.ticket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketDto {

    public enum Status
    {
        CHECK,
        SUCCESS,
        REJECTION
    }

    @Null
    private UUID id;

    // extracted from JWT
    @JsonIgnore
    private UUID userId;  // not foreign_key

    @Null
    private Status status;

    @NotNull
    @JsonProperty("from_station_id")
    private UUID fromStationId;

    @NotNull
    @JsonProperty("to_station_id")
    private UUID toStationId;

    @Null
    private Timestamp created;
}
