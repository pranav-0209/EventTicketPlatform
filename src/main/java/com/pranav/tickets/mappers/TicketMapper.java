package com.pranav.tickets.mappers;

import com.pranav.tickets.domain.entities.Ticket;
import com.pranav.tickets.domain.entities.TicketType;
import com.pranav.tickets.dtos.GetTicketResponseDto;
import com.pranav.tickets.dtos.ListTicketResponseDto;
import com.pranav.tickets.dtos.ListTicketTypeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    ListTicketTypeResponseDto toListTicketTypeResponseDto(TicketType ticketType);

    ListTicketResponseDto toListTicketResponseDto(Ticket ticket);

    @Mapping(target = "price", source = "TicketType.price")
    @Mapping(target = "description", source = "TicketType.description")
    @Mapping(target = "eventName", source = "TicketType.event.name")
    @Mapping(target = "eventVenue", source = "TicketType.event.venue")
    @Mapping(target = "eventStart", source = "TicketType.event.start")
    @Mapping(target = "eventEnd", source = "TicketType.event.end")
    GetTicketResponseDto toGetTicketResponseDto(Ticket ticket);
}
