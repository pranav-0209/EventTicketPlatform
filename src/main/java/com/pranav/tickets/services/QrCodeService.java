package com.pranav.tickets.services;

import com.pranav.tickets.domain.entities.QrCode;
import com.pranav.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

    byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);
}
