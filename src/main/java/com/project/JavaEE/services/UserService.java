package com.project.JavaEE.services;

import com.project.JavaEE.entities.TicketEntity;
import com.project.JavaEE.entities.PermissionEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.repositories.TicketRepository;
import com.project.JavaEE.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TicketRepository bookRepository;

    @Transactional
    public UserEntity create(final String username, final String password, final List<PermissionEntity> permissions) {
        final UserEntity user = new UserEntity();
        user.setLogin(username);
        user.setPassword(password);
        user.setLiked(new HashSet<>());
        user.setPermissions(permissions);
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public Set<TicketEntity> getLiked(final String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        return user.getLiked();
    }

    @Transactional
    public UserEntity addLiked(final String username, final int book_id) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        final TicketEntity book = bookRepository.findById(book_id).orElseThrow(() -> new EntityNotFoundException("The book with id " + book_id + " was not found"));
        Set<TicketEntity> books = new HashSet<>(user.getLiked());
        books.add(book);
        user.setLiked(books);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Transactional
    public UserEntity removeLiked(final String username, final int bookId) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        user.setLiked(user.getLiked().stream().filter(book -> book.getId() != bookId).collect(Collectors.toSet()));
        userRepository.saveAndFlush(user);
        return user;
    }
}
