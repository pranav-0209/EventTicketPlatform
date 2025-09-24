package com.pranav.tickets.mappers;

import com.pranav.tickets.domain.entities.TicketValidation;
import com.pranav.tickets.dtos.TicketValidationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketValidationMapper {

    @Mapping(target = "ticketId", source = "ticket.id")
    TicketValidationResponseDto ticketValidationResponseDto(TicketValidation ticketValidation);
}
