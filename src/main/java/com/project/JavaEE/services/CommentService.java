package com.project.JavaEE.services;

import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentEntity createComment(String bodyText, LocalDateTime creationDate,
                                       TicketEntity ticket, UserEntity author) {
        final CommentEntity comment = new CommentEntity();
        comment.setBodyText(bodyText);
        comment.setCreationDate(creationDate);
        comment.setTicket(ticket);
        comment.setAuthor(author);
        return commentRepository.saveAndFlush(comment);
    }

    @Transactional
    public List<CommentEntity> getByTicketId(Integer ticketId) {
        return commentRepository.findAllByTicketId(ticketId);
    }

    @Transactional
    public CommentEntity getByCommentId(int id) {
        return commentRepository.get(id).orElse(null);
    }

}
