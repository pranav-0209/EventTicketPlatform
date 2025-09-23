package com.pranav.tickets.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketTypeResponseDto {

    private UUID id;
    private String name;
    private Double price;
}
