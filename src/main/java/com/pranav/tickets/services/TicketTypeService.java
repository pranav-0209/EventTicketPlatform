package com.pranav.tickets.services;

import com.pranav.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface TicketTypeService {

    Ticket purchaseTicket(UUID userId, UUID ticketTypeId);
}
