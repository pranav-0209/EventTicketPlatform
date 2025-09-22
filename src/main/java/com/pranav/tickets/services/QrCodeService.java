package com.pranav.tickets.services;

import com.pranav.tickets.domain.entities.QrCode;
import com.pranav.tickets.domain.entities.Ticket;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

}
