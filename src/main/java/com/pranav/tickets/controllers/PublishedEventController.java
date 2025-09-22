package com.pranav.tickets.controllers;

import com.pranav.tickets.dtos.ListPublishedEventResponseDto;
import com.pranav.tickets.mappers.EventMapper;
import com.pranav.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/published-events")
@RequiredArgsConstructor
public class PublishedEventController {

    private final EventService eventService;
    private final EventMapper  eventMapper;

    @GetMapping
    public ResponseEntity<Page<ListPublishedEventResponseDto>> listPublishedEvents(Pageable pageable) {

    return ResponseEntity.ok(eventService.listPublishedEvents(pageable)
            .map(eventMapper::toListPublishedEventResponseDto));
    }
}
