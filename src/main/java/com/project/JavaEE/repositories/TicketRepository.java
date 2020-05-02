package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    @Query("SELECT b FROM TicketEntity b WHERE b.title LIKE :title")
    List<TicketEntity> getByTitle(@Param("title") String title);

    @Query("SELECT b FROM TicketEntity b WHERE b.author LIKE :author ")
    List<TicketEntity> getByAuthor(@Param("author") String author);

    @Query("SELECT b FROM TicketEntity b WHERE b.isbn LIKE :isbn ")
    List<TicketEntity> getByIsbn(@Param("isbn") String isbn);
}
