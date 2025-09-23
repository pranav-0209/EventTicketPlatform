package com.pranav.tickets.mappers;

import com.pranav.tickets.domain.entities.Ticket;
import com.pranav.tickets.domain.entities.TicketType;
import com.pranav.tickets.dtos.ListTicketResponseDto;
import com.pranav.tickets.dtos.ListTicketTypeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    ListTicketTypeResponseDto  toListTicketTypeResponseDto(TicketType ticketType);

    ListTicketResponseDto  toListTicketResponseDto(Ticket ticket);
}
