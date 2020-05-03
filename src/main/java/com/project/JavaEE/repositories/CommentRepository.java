package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query("SELECT comment " +
            "FROM CommentEntity comment " +
            "LEFT JOIN FETCH comment.ticket " +
            "LEFT JOIN FETCH comment.author " +
            "WHERE comment.id = :id")
    Optional<CommentEntity> get(@Param("id") Integer id);

}
