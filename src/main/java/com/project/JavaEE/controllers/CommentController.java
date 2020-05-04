package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.CommentDto;
import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.services.CommentService;
import com.project.JavaEE.services.TicketService;
import com.project.JavaEE.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isFullyAuthenticated()")
public class CommentController {

    private final CommentService commentService;
    private final TicketService ticketService;
    private final UserService userService;

    public CommentController(CommentService commentService, TicketService ticketService, UserService userService) {
        this.commentService = commentService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PostMapping(value = "/add-comment")
    public List<CommentEntity> create(@Valid @RequestBody final CommentDto commentModel) {
        TicketEntity auxTicket = ticketService.getById(commentModel.getTicketId());
        UserEntity auxUser = userService.getByUsername(commentModel.getAuthorUsername());
        commentService.createComment(commentModel.getBodyText(), auxTicket, auxUser);
        return commentService.getByTicketId(commentModel.getTicketId());
    }

    @GetMapping(value = "/ticket/{ticketId}/comments")
    public List<CommentEntity> getTicketComments(@PathVariable("ticketId") Integer ticketId) {
        return commentService.getByTicketId(ticketId);
    }


}
