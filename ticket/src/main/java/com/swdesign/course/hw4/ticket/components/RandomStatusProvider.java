package com.swdesign.course.hw4.ticket.components;

import com.swdesign.course.hw4.ticket.dto.TicketDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStatusProvider {

    List<TicketDto.Status> statusList = new ArrayList<>(){{
        add(TicketDto.Status.CHECK);
        add(TicketDto.Status.REJECTION);
        add(TicketDto.Status.SUCCESS);
    }};

    List<Double> wieghtList = new ArrayList<>(){{
        add(0.05);
        add(0.25);
        add(0.7);
    }};

    Random rand = new Random();

    public TicketDto.Status getRandomStatus() {

        double exp = rand.nextDouble();

        double percentile = 0;
        for (int index = 0; index < statusList.size(); index++) {
            percentile += wieghtList.get(index);
            if (percentile > exp) {
                return statusList.get(index);
            }
        }
        // unreachable
        return null;
    }
}
