package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("SELECT b FROM Ticket b WHERE b.title LIKE :title")
    List<Ticket> getByTitle(@Param("title") String title);

    @Query("SELECT b FROM Ticket b WHERE b.author LIKE :author ")
    List<Ticket> getByAuthor(@Param("author") String author);

    @Query("SELECT b FROM Ticket b WHERE b.isbn LIKE :isbn ")
    List<Ticket> getByIsbn(@Param("isbn") String isbn);
}
