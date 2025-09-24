package com.pranav.tickets.controllers;

import com.pranav.tickets.domain.entities.TicketValidation;
import com.pranav.tickets.domain.entities.TicketValidationMethod;
import com.pranav.tickets.dtos.TicketValidationRequestDto;
import com.pranav.tickets.dtos.TicketValidationResponseDto;
import com.pranav.tickets.mappers.TicketValidationMapper;
import com.pranav.tickets.services.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/ticket-validations")
@RequiredArgsConstructor
public class TicketValidationController {

    private final TicketValidationService ticketValidationService;
    private final TicketValidationMapper ticketValidationMapper;

    @PostMapping()
    public ResponseEntity<TicketValidationResponseDto> validateTicket(
            @RequestBody TicketValidationRequestDto ticketValidationRequestDto) {

        TicketValidationMethod method = ticketValidationRequestDto.getMethod();
        TicketValidation ticketValidation;

        if (TicketValidationMethod.MANUAL.equals(method)) {
            ticketValidation = ticketValidationService.validateTicketManually(ticketValidationRequestDto.getId());
        } else {
            ticketValidation = ticketValidationService.validateTicketByQrCode(ticketValidationRequestDto.getId());
        }
        return ResponseEntity.ok(ticketValidationMapper.ticketValidationResponseDto(ticketValidation));
    }
}
