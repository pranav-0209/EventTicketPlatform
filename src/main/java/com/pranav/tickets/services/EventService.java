package com.pranav.tickets.services;


import com.pranav.tickets.domain.CreateEventRequest;
import com.pranav.tickets.domain.CreateTicketTypeRequest;
import com.pranav.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequest event);
}
