package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT user " +
            "FROM UserEntity user " +
            "LEFT JOIN FETCH user.permissions " +
            "JOIN FETCH user.requested " +
            "JOIN FETCH user.responsibleFor " +
            "JOIN FETCH user.comments " +
            "WHERE user.login = :login")
    Optional<UserEntity> get(@Param("login") String login);

}