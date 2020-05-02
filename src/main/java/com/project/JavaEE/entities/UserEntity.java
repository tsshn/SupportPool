package com.project.JavaEE.entities;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_to_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<PermissionEntity> permissions;

    @OneToMany(mappedBy="UserEntity")
    private Set<TicketEntity> responsibleFor;

    @OneToMany(mappedBy="UserEntity")
    private Set<TicketEntity> requested;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "responsible_user_to_tickets",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private Set<TicketEntity> responsibleFor;*/

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "requester_user_to_tickets",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private Set<TicketEntity> requested;*/

}