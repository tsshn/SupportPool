package com.project.JavaEE.controllers;

import com.project.JavaEE.services.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    private final TicketService ticketService;

    public PageController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

//    @GetMapping(value = {"/login", "/"})
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @GetMapping("/pool")
    public String pool() {
        return "pool";
    }

    @PreAuthorize("hasAuthority('VIEW_ADMIN')")
    @GetMapping("/team")
    public String team() {
        return "team";
    }

    @RequestMapping(value = "/ticket/{id}")
    public String ticket(@PathVariable("id") int id, Model model) {
        model.addAttribute("ticket", ticketService.getById(id));
        return "ticket";
    }

}
