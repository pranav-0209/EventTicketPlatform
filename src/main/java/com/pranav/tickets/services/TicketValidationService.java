package com.pranav.tickets.services;

import com.pranav.tickets.domain.entities.TicketValidation;

import java.util.UUID;

public interface TicketValidationService {

    TicketValidation validateTicketByQrCode(UUID qrCodeId);
    TicketValidation validateTicketManually(UUID ticketId);
}
