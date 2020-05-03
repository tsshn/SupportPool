package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT user FROM UserEntity user LEFT JOIN FETCH user.permissions INNER JOIN user.responsibleFor INNER JOIN user.requested INNER JOIN user.comments WHERE user.login = :login")
    Optional<UserEntity> get(@Param("login") String login);

}