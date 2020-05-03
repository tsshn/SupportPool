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
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ticket")
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;

    @Column(name = "body_text")
    private String bodyText;

    @Column(name = "creation_date")
    private Date creationDate;

}
