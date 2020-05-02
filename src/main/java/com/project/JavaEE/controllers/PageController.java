package com.project.JavaEE.controllers;

import com.project.JavaEE.services.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private final TicketService bookService;

    public PageController(TicketService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @GetMapping("/cabinet")
    public String profile() {
        return "cabinet";
    }

    @GetMapping("/library")
    public String bookCatalog() {
        return "library";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
