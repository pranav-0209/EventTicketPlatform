package com.pranav.tickets.services.impl;

import com.pranav.tickets.domain.entities.Ticket;
import com.pranav.tickets.domain.entities.TicketStatusEnum;
import com.pranav.tickets.domain.entities.TicketType;
import com.pranav.tickets.domain.entities.User;
import com.pranav.tickets.exceptions.TicketTypeNotFoundException;
import com.pranav.tickets.exceptions.TicketsSoldOutException;
import com.pranav.tickets.exceptions.UserNotFoundException;
import com.pranav.tickets.repositories.TicketRepository;
import com.pranav.tickets.repositories.TicketTypeRepository;
import com.pranav.tickets.repositories.UserRepository;
import com.pranav.tickets.services.QrCodeService;
import com.pranav.tickets.services.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(String.format("User with id: %s not found", userId)));

        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId).orElseThrow(()
                -> new TicketTypeNotFoundException(String.format("Ticket type with id: %s not found", ticketTypeId)));

        int purchasedTickets = ticketRepository.countByTicketTypeID(ticketType.getId());
        Integer totalAvailable = ticketType.getTotalAvailable();

        if (purchasedTickets + 1 > totalAvailable) {
            throw new TicketsSoldOutException();
        }

        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        return ticketRepository.save(savedTicket);

    }
}
