package com.project.JavaEE.controllers;

import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.services.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isFullyAuthenticated()")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/ticket/{ticketId}/comments")
    public List<CommentEntity> getTicketComments(@PathVariable("ticketId") Integer ticketId) {
        return commentService.getByTicketId(ticketId);
    }


}
