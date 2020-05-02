package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.TicketDto;
import com.project.JavaEE.dto.FilterDto;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isFullyAuthenticated()")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @ResponseBody
    @GetMapping(value = "/get")
    public List<TicketEntity> getAll() {
        return ticketService.getAll();
    }

    @GetMapping(value = "/get/{ticketId}")
    public ResponseEntity<TicketEntity> getById(@PathVariable("ticketId") Integer ticketId) {
        return ResponseEntity.ok(ticketService.getById(ticketId));
    }

    @PreAuthorize("hasAuthority('VIEW_SALES')")
    @PostMapping(value = "/create")
    public void create(@Valid @RequestBody final TicketDto ticketModel) {
        ticketService.createTicket(ticketModel.getTitle(), ticketModel.getBodyText(),
                                ticketModel.getState(), ticketModel.getPriority(),
                                ticketModel.getCasetype(), ticketModel.getCreationDate(),
                                ticketModel.getEtaDate(), ticketModel.getNextstepDate(),
                                ticketModel.getNextstepNote(), ticketModel.getFirm());
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<TicketEntity>> filter(@RequestBody final FilterDto filterDto) {
        return ResponseEntity.ok(ticketService.filter(filterDto.getProperty(), filterDto.getInput()));
    }
}
