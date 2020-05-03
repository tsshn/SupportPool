package com.project.JavaEE.entities;

import com.project.JavaEE.entities.type.Case;
import com.project.JavaEE.entities.type.Priority;
import com.project.JavaEE.entities.type.State;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tickets")
@RequiredArgsConstructor
@Data
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "responsible_user")
    private UserEntity responsibleUser;

    @ManyToOne
    @JoinColumn(name = "requester_user")
    private UserEntity requesterUser;

    @OneToMany(mappedBy = "ticket")
    private Set<CommentEntity> comments;

    @Column(name = "body_text", nullable = false)
    private String bodyText;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_type", nullable = false)
    private Case caseType;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "eta_date")
    private Date etaDate;

    @Column(name = "ns_date")
    private Date nextStepDate;

    @Column(name = "ns_note")
    private String nextStepNote;

    @Column(name = "firm", nullable = false)
    private String firm;

}
