package com.pranav.tickets.dtos;

import com.pranav.tickets.domain.entities.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketResponseDto {

    private UUID userId;
    private TicketStatusEnum status;
    private ListTicketTypeResponseDto ticketType;

}
