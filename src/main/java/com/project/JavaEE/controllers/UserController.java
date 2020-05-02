package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.UserDto;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.PermissionEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.entities.UserDetails;
import com.project.JavaEE.services.UserService;
import com.project.JavaEE.entities.type.Permission;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("isFullyAuthenticated()")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize(value = "'VIEW_ADMIN'") // <-- NOT SURE ABOUT THAT PART
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> create(@Valid @RequestBody final UserDto userModel, Permission permission) {
        List<PermissionEntity> permissions = new ArrayList<>();
        permissions.add(new PermissionEntity(1, permission));
        UserEntity user = userService.create(userModel.getLogin(), userModel.getPassword(), permissions);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getPassword(), user.getLogin()));
    }

    @GetMapping("/details")
    public ResponseEntity<UserDetails> getDetails() {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping(value = "/connected/{type}")
    public ResponseEntity<Set<TicketEntity>> getTickets(@PathVariable String type) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getTickets(userDetails.getUsername(), type));
    }

    @RequestMapping(value = "/connected/{type}/{ticketId}", method = RequestMethod.POST)
    public ResponseEntity<Set<TicketEntity>> addTicket(@PathVariable String ticketId, @PathVariable String type) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userService.addTicket(userDetails.getUsername(), type, Integer.parseInt(ticketId));
        return ResponseEntity.ok(userEntity.getResponsibleFor());
    }

    @RequestMapping(value = "/connected/{type}/{ticketId}", method = RequestMethod.DELETE)
    public ResponseEntity<Set<TicketEntity>> removeTicket(@PathVariable String ticketId, @PathVariable String type) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userService.removeTicket(userDetails.getUsername(), type, Integer.parseInt(ticketId));
        return ResponseEntity.ok(userEntity.getResponsibleFor());
    }

}