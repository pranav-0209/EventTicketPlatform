package com.pranav.tickets.mappers;

import com.pranav.tickets.domain.CreateEventRequest;
import com.pranav.tickets.domain.CreateTicketTypeRequest;
import com.pranav.tickets.domain.entities.Event;
import com.pranav.tickets.dtos.CreateEventRequestDto;
import com.pranav.tickets.dtos.CreateEventResponseDto;
import com.pranav.tickets.dtos.CreateTicketTypeRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}
