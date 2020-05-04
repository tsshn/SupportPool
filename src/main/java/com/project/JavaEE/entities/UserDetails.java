package com.project.JavaEE.entities;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class UserDetails extends User {

    public UserDetails(
            final String username,
            final String password,
            final List<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
