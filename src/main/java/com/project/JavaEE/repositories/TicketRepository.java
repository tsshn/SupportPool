package com.project.JavaEE.repositories;

import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.type.Case;
import com.project.JavaEE.entities.type.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    @Query("SELECT t FROM TicketEntity t WHERE t.title LIKE :title")
    List<TicketEntity> findByTitle(@Param("title") String title);

    @Query("SELECT t FROM TicketEntity t WHERE t.state = :state")
    List<TicketEntity> findByState(@Param("state") State state);

    @Query("SELECT t FROM TicketEntity t WHERE t.casetype = :casetype")
    List<TicketEntity> findByCase(@Param("casetype") Case casetype);

    @Query("SELECT t FROM TicketEntity t WHERE t.firm LIKE :firm")
    List<TicketEntity> findByFirm(@Param("firm") String firm);
}
