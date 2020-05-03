package com.project.JavaEE.services;

import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.entities.type.Case;
import com.project.JavaEE.entities.type.Priority;
import com.project.JavaEE.entities.type.State;
import com.project.JavaEE.repositories.CommentRepository;
import com.project.JavaEE.repositories.TicketRepository;
import com.project.JavaEE.repositories.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Transactional
    public TicketEntity createTicket(String title, String body_text, State state,
                                     Priority priority, Case caseType, Date creationDate,
                                     Date etaDate, Date nextStepDate,
                                     String nextStepNote, String firm) {
        final TicketEntity ticket = new TicketEntity();
        ticket.setTitle(title);
        ticket.setBodyText(body_text);
        ticket.setState(state);
        ticket.setPriority(priority);
        ticket.setCaseType(caseType);
        ticket.setCreationDate(creationDate);
        ticket.setEtaDate(etaDate);
        ticket.setNextStepDate(nextStepDate);
        ticket.setNextStepNote(nextStepNote);
        ticket.setFirm(firm);
        ticket.setComments(new HashSet<>());
        return ticketRepository.saveAndFlush(ticket);
    }

    @Transactional
    public TicketEntity updateTicket(Integer ticketId, String title, String body_text, State state,
                                     Priority priority, Case caseType, Date creationDate,
                                     Date etaDate, Date nextStepDate, String nextStepNote, String firm) throws EntityNotFoundException {
        final TicketEntity ticket = ticketRepository.get(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket with id \"" + ticketId + "\" was not found"));
        ticket.setTitle(title);
        ticket.setBodyText(body_text);
        ticket.setState(state);
        ticket.setPriority(priority);
        ticket.setCaseType(caseType);
        ticket.setCreationDate(creationDate);
        ticket.setEtaDate(etaDate);
        ticket.setNextStepDate(nextStepDate);
        ticket.setNextStepNote(nextStepNote);
        ticket.setFirm(firm);
        //ticket.setComments(new HashSet<>());
        return ticketRepository.saveAndFlush(ticket);
    }

    @Transactional
    public Set<CommentEntity> getTicketComments(final Integer ticketId) throws NotFoundException {
        final TicketEntity ticket = ticketRepository.get(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket with id \"" + ticketId + "\" not found"));
        return ticket.getComments();
    }

    @Transactional
    public TicketEntity addComment(final Integer commentId,
                                    final String username,
                                    final Integer ticketId) {

        final TicketEntity ticket = ticketRepository.get(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket with id " + ticketId + " not found"));

        final CommentEntity newComment = commentRepository.get(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id \"" + commentId + "\" not found"));

        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with login \"" + username + "\" not found"));

        Set<CommentEntity> currentComments = new HashSet<>(ticket.getComments());

        newComment.setAuthor(user);
        currentComments.add(newComment);
        ticket.setComments(currentComments);
        ticketRepository.saveAndFlush(ticket);
        return ticket;
    }

    @Transactional
    public TicketEntity getById(int id) {
        Optional<TicketEntity> ticket = ticketRepository.get(id);
        return ticket.orElse(null);
    }

    @Transactional
    public List<TicketEntity> filter(String property, String query) {
        if (query.equals("")) {
            return getAll();
        }
        switch (property) {
            case "title":
                return getByTitle(query);
            case "firm":
                return getByFirm(query);
            case "state":
                State state = State.values()[Integer.parseInt(query)];
                return getByState(state);
            case "priority":
                Priority priority = Priority.values()[Integer.parseInt(query)];
                return getByPriority(priority);
            case "case":
                Case casetype = Case.values()[Integer.parseInt(query)];
                return getByCase(casetype);
            default:
                return getAll();
        }
    }

    @Transactional
    public List<TicketEntity> getAll() {
        return ticketRepository.findAll();
    }

    @Transactional
    public List<TicketEntity> getByTitle(String title) { return ticketRepository.findByTitle('%' + title + '%'); }

    @Transactional
    public List<TicketEntity> getByState(State state) { return ticketRepository.findByState(state); }

    @Transactional
    public List<TicketEntity> getByCase(Case caseType) { return ticketRepository.findByCase(caseType); }

    @Transactional
    public List<TicketEntity> getByFirm(String firm) { return ticketRepository.findByFirm('%' + firm + '%'); }

    @Transactional
    public List<TicketEntity> getByPriority(Priority priority) { return ticketRepository.findByPriority(priority); }
}
