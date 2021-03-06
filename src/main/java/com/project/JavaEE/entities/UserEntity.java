package com.project.JavaEE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "responsibleUser")
    private Set<TicketEntity> responsibleFor;

    @JsonIgnore
    @OneToMany(mappedBy = "requesterUser")
    private Set<TicketEntity> requested;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Set<CommentEntity> comments;

}