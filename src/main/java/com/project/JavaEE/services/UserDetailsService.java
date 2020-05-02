package com.project.JavaEE.services;

import com.project.JavaEE.entities.Permission;
import com.project.JavaEE.entities.User;
import com.project.JavaEE.entities.UserDetails;
import com.project.JavaEE.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    private static List<GrantedAuthority> mapAuthorities(final List<Permission> permissions) {
        return permissions.stream()
                .map(Permission::getPermission)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));
        return new UserDetails(
                username,
                user.getPassword(),
                mapAuthorities(user.getPermissions()),
                user.getLiked()
        );
    }
}
