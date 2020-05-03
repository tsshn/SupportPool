package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.TicketDto;
import com.project.JavaEE.dto.FilterDto;
import com.project.JavaEE.dto.UserDto;
import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.UserDetails;
import com.project.JavaEE.services.CommentService;
import com.project.JavaEE.services.TicketService;
import com.project.JavaEE.services.UserService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@PreAuthorize("isFullyAuthenticated()")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private final CommentService commentService;

    public TicketController(TicketService ticketService,
                            UserService userService,
                            CommentService commentService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @ResponseBody
    @GetMapping(value = "/tickets")
    public List<TicketEntity> getAll() {
        return ticketService.getAll();
    }

    @GetMapping(value = "/getTicket/{ticketId}")
    public ResponseEntity<TicketEntity> getById(@PathVariable("ticketId") Integer ticketId) {
        return ResponseEntity.ok(ticketService.getById(ticketId));
    }

    @PostMapping(value = "/update-ticket/{ticketId}")
    public void update(@PathVariable("ticketId") Integer ticketId,
                       @Valid @RequestBody final TicketDto ticketModel,
                       @Valid @RequestBody final UserDto respUserModel,
                       @Valid @RequestBody final UserDto reqUserModel) {
        ticketService.updateTicket(ticketId, ticketModel.getTitle(), ticketModel.getBodyText(),
                                ticketModel.getState(), ticketModel.getPriority(),
                                ticketModel.getCaseType(), ticketModel.getCreationDate(),
                                ticketModel.getEtaDate(), ticketModel.getNextStepDate(),
                                ticketModel.getNextStepNote(), ticketModel.getFirm());
        userService.addTicket(respUserModel.getLogin(), "responsible", ticketId);
        userService.addTicket(reqUserModel.getLogin(), "requester", ticketId);
    }

    @PreAuthorize("hasAuthority('VIEW_SALES')")
    @PostMapping(value = "/create-ticket")
    public void create(@Valid @RequestBody final TicketDto ticketModel,
                       @Valid @RequestBody final UserDto respUserModel,
                       @Valid @RequestBody final UserDto reqUserModel) {
        TicketEntity newTicket = ticketService.createTicket(ticketModel.getTitle(), ticketModel.getBodyText(),
                                ticketModel.getState(), ticketModel.getPriority(),
                                ticketModel.getCaseType(), ticketModel.getCreationDate(),
                                ticketModel.getEtaDate(), ticketModel.getNextStepDate(),
                                ticketModel.getNextStepNote(), ticketModel.getFirm());
        userService.addTicket(respUserModel.getLogin(), "responsible", newTicket.getId());
        userService.addTicket(reqUserModel.getLogin(), "requester", newTicket.getId());
    }

    @PostMapping(value = "/filterTickets")
    public ResponseEntity<List<TicketEntity>> filter(@RequestBody final FilterDto filterDto) {
        return ResponseEntity.ok(ticketService.filter(filterDto.getProperty(), filterDto.getQuery()));
    }

    @GetMapping(value = "/{ticketId}/comments")
    public ResponseEntity<Set<CommentEntity>> getTickets(@PathVariable Integer ticketId) throws NotFoundException {
        return ResponseEntity.ok(ticketService.getTicketComments(ticketId));
    }

    @PostMapping(value = "/{ticketId}/comments/{commentId}")
    public ResponseEntity<Set<CommentEntity>> addComment(@PathVariable Integer ticketId,
                                                         @PathVariable Integer commentId) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TicketEntity ticketEntity = ticketService.addComment(commentId, userDetails.getUsername(), ticketId);
        return ResponseEntity.ok(ticketEntity.getComments());
    }
}
