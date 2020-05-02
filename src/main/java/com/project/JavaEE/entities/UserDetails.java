package com.project.JavaEE.entities;

import lombok.Getter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

@Getter
@ToString(callSuper = true)
public class UserDetails extends User {

    private final Set<TicketEntity> responsibleFor;
    private final Set<TicketEntity> requested;
    private final Set<CommentEntity> comments;

    public UserDetails(
            final String username,
            final String password,
            final List<? extends GrantedAuthority> authorities,
            final Set<TicketEntity> responsibleFor,
            final Set<TicketEntity> requested,
            final Set<CommentEntity> comments) {
        super(username, password, authorities);
        this.responsibleFor = responsibleFor;
        this.requested = requested;
        this.comments = comments;
    }

}
