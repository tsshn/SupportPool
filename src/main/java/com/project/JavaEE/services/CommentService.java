package com.project.JavaEE.services;

import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.repositories.CommentRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public CommentEntity getById(int id) {
        Optional<CommentEntity> comment = commentRepository.get(id);
        return comment.orElse(null);
    }

}
