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
    private final Set<TicketEntity> liked;

    public UserDetails(
            final String username,
            final String password,
            final List<? extends GrantedAuthority> authorities,
            final Set<TicketEntity> liked) {
        super(username, password, authorities);
        this.liked = liked;
    }
}
