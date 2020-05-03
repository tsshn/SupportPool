package com.project.JavaEE.services;

import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.repositories.CommentRepository;
import com.project.JavaEE.repositories.TicketRepository;
import com.project.JavaEE.repositories.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentEntity createComment(String bodyText, Date creationDate) {
        final CommentEntity comment = new CommentEntity();
        comment.setBodyText(bodyText);
        comment.setCreationDate(creationDate);
        return commentRepository.saveAndFlush(comment);
    }

//    @Transactional
//    public Set<CommentEntity> getTicketComments(final Integer ticketId) throws NotFoundException {
//        final TicketEntity ticket = ticketRepository.get(ticketId)
//                .orElseThrow(() -> new NotFoundException("Ticket with id \"" + ticketId + "\" not found"));
//        return ticket.getComments();
//    }
//
//    @Transactional
//    public CommentEntity addComment(final Integer commentId,
//                                    final String username,
//                                    final Integer ticketId) {
//        final CommentEntity comment = commentRepository.get(commentId)
//                .orElseThrow(() -> new EntityNotFoundException("Comment with id \"" + commentId + "\" not found"));
//        final UserEntity user = userRepository.get(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User with login \"" + username + "\" not found"));
//        final TicketEntity ticket = ticketRepository.get(ticketId)
//                .orElseThrow(() -> new EntityNotFoundException("Ticket with id " + ticketId + " not found"));
//
//        comment.setTicket(ticket);
//        comment.setAuthor(user);
//        commentRepository.saveAndFlush(comment);
//        return comment;
//    }

    @Transactional
    public CommentEntity getById(int id) {
        Optional<CommentEntity> comment = commentRepository.get(id);
        return comment.orElse(null);
    }

}
