package com.project.JavaEE.services;

import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.PermissionEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.repositories.TicketRepository;
import com.project.JavaEE.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @javax.transaction.Transactional
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity create(final String username, final String password, final List<PermissionEntity> permissions) {
        final UserEntity user = new UserEntity();
        user.setLogin(username);
        user.setPassword(password);
        user.setResponsibleFor(new HashSet<>());
        user.setPermissions(permissions);
        return userRepository.saveAndFlush(user);
    }

    /*@javax.transaction.Transactional
    public List<UserEntity> filter(String criteria, String query) {
        return switch (criteria) {
            case "login" -> userRepository.filterByLogin(query);
            case "role" -> userRepository.filterByRole(query);
            default -> getAll();
        };
    }*/

    @Transactional
    public List<UserEntity> removeUser(final String userId) throws UsernameNotFoundException {
        userRepository.delete(userRepository.getOne(Integer.parseInt(userId)));
        return userRepository.findAll();
    }

    @Transactional
    public String getLogin(final String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        return user.getLogin();
    }

    @Transactional
    public Set<TicketEntity> getTickets(final String username, final String type) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        return switch (type) {
            case "responsible" -> user.getResponsibleFor();
            default -> user.getRequested();
        };
    }

    @Transactional
    public UserEntity addTicket(final String username, final String type, final int ticketId) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        final TicketEntity ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("The ticket with id " + ticketId + " was not found"));
        Set<TicketEntity> tickets = switch (type) {
            case "responsible" -> new HashSet<>(user.getResponsibleFor());
            default -> new HashSet<>(user.getRequested());
        };
        tickets.add(ticket);
        user.setResponsibleFor(tickets);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Transactional
    public UserEntity removeTicket(final String username, final String type, final int ticketId) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        switch (type) {
            case "responsible" -> user.setResponsibleFor(user.getResponsibleFor().stream().filter(ticket -> ticket.getId() != ticketId).collect(Collectors.toSet()));
            default -> user.setRequested(user.getRequested().stream().filter(ticket -> ticket.getId() != ticketId).collect(Collectors.toSet()));
        }
        userRepository.saveAndFlush(user);
        return user;
    }

}
