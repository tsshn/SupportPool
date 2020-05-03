package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT user " +
            "FROM UserEntity user " +
            "LEFT JOIN FETCH user.permissions " +
            "LEFT JOIN FETCH user.responsibleFor " +
            "LEFT JOIN FETCH user.requested " +
            "LEFT JOIN FETCH user.comments " +
            "WHERE user.login = :login")
    Optional<UserEntity> get(@Param("login") String login);

    /*@Query("SELECT user FROM UserEntity user WHERE user.login LIKE :login")
    List<UserEntity> filterByLogin(@Param("login") String login);

    @Query("SELECT user FROM UserEntity user WHERE :permission HAVING user.permissions")
    List<UserEntity> filterByRole(@Param("permission") String permission);*/

}