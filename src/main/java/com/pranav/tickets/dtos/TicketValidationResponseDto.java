package com.pranav.tickets.dtos;

import com.pranav.tickets.domain.entities.TicketValidation;
import com.pranav.tickets.domain.entities.TicketValidationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationResponseDto {
    private UUID ticketId;
    private TicketValidationStatusEnum  status;

}
