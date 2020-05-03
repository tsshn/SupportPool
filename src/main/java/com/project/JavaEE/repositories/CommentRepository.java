package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.CommentEntity;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository {

    Optional<CommentEntity> get(@Param("id") Integer id);

}
