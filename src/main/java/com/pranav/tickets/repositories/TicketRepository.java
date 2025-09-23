package com.pranav.tickets.repositories;

import com.pranav.tickets.domain.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    int countByTicketTypeID(UUID ticketTypeID);

    Page<Ticket> findByPurchaserId(UUID purchaserId, Pageable pageable);
}
