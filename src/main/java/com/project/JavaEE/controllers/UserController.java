package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.UserDto;
import com.project.JavaEE.entities.Ticket;
import com.project.JavaEE.entities.Permission;
import com.project.JavaEE.entities.User;
import com.project.JavaEE.entities.UserDetails;
import com.project.JavaEE.services.UserService;
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

    @PreAuthorize(value = "!isFullyAuthenticated()")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> create(@Valid @RequestBody final UserDto userModel) {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission(1, com.project.JavaEE.entities.type.Permission.VIEW_CATALOG));
        User user = userService.create(userModel.getLogin(), userModel.getPassword(), permissions);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getPassword(), user.getLogin()));
    }

    @GetMapping("/details")
    public ResponseEntity<UserDetails> getDetails() {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping(value = "/liked")
    public ResponseEntity<Set<Ticket>> getLiked() {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getLiked(userDetails.getUsername()));
    }

    @RequestMapping(value = "/liked/{bookId}", method = RequestMethod.POST)
    public ResponseEntity<Set<Ticket>> addLiked(@PathVariable String bookId) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEntity = userService.addLiked(userDetails.getUsername(), Integer.parseInt(bookId));
        return ResponseEntity.ok(userEntity.getLiked());
    }

    @RequestMapping(value = "/liked/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<Set<Ticket>> removeLiked(@PathVariable String bookId) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEntity = userService.removeLiked(userDetails.getUsername(), Integer.parseInt(bookId));
        return ResponseEntity.ok(userEntity.getLiked());
    }
}