package com.project.JavaEE.services;

import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.type.Case;
import com.project.JavaEE.entities.type.Priority;
import com.project.JavaEE.entities.type.State;
import com.project.JavaEE.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

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
        ticket.setComments(new HashSet<>());
        return ticketRepository.saveAndFlush(ticket);
    }

    @Transactional
    public TicketEntity getById(int id) {
        Optional<TicketEntity> ticket = ticketRepository.get(id);
        return ticket.orElse(null);
    }

    @Transactional
    public List<TicketEntity> filter(String criteria, String filterInput) {
        return switch (criteria) {
            case "title" -> getByTitle(filterInput);
            case "firm" -> getByFirm(filterInput);
            case "state" -> getByState(State.valueOf(filterInput.toUpperCase()));
            case "case" -> getByCase(Case.valueOf(filterInput.toUpperCase()));
            default -> getAll();
        };
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
}
