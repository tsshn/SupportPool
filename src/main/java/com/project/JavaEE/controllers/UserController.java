package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.UserDto;
import com.project.JavaEE.dto.UserFilterDto;
import com.project.JavaEE.entities.PermissionEntity;
import com.project.JavaEE.entities.UserDetails;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.entities.type.Permission;
import com.project.JavaEE.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isFullyAuthenticated()")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('VIEW_ADMIN')")
    @RequestMapping(value = "/createUser/{permission}", method = RequestMethod.POST)
    public ResponseEntity<UserDto> create(@Valid @RequestBody final UserDto userDto, @PathVariable int permission) {
        List<PermissionEntity> permissions = new ArrayList<>();
        permissions.add(new PermissionEntity(permission, Permission.VIEW_ADMIN));
        UserEntity user = userService.create(userDto.getLogin(), userDto.getPassword(), permissions);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getPassword(), user.getLogin()));
    }

    @RequestMapping(value = "/filterUsers", method = {RequestMethod.POST})
    public ResponseEntity<List<UserEntity>> filter(@RequestBody final UserFilterDto filterDto) {
        return ResponseEntity.ok(userService.filter(filterDto.getProperty(), filterDto.getQuery()));
    }

    @RequestMapping(value = "/removeUser/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<List<UserEntity>> removeUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.removeUser(userId));
    }

    @ResponseBody
    @GetMapping(value = "/getUsers")
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/getLogin")
    public ResponseEntity<String> getLogin() {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getLogin(userDetails.getUsername()));
    }

    @GetMapping("/details")
    public ResponseEntity<UserDetails> getDetails() {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

}