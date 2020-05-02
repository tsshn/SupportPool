package com.project.JavaEE.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@RequiredArgsConstructor
@Data
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private TicketEntity ticket;
    
    @ManyToOne
    private UserEntity author;

    @Column(name = "body_text")
    private String bodyText;

    @Column(name = "creation_date")
    private Date creationDate;
}
