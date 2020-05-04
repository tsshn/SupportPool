package com.project.JavaEE.services;

import com.project.JavaEE.entities.PermissionEntity;
import com.project.JavaEE.entities.UserEntity;
import com.project.JavaEE.entities.type.Permission;
import com.project.JavaEE.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @javax.transaction.Transactional
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity create(final String username, final String password, final List<PermissionEntity> permissions) {
        final UserEntity user = new UserEntity();
        user.setLogin(username);
        user.setPassword(password);
        user.setPermissions(permissions);
        user.setComments(new HashSet<>());
        return userRepository.saveAndFlush(user);
    }

    @javax.transaction.Transactional
    public List<UserEntity> filter(String property, String query) {
        if(query.equals(""))
            return getAll();
        switch (property) {
            case "login":
                return userRepository.filterByLogin('%' + query + '%');
            case "permission":
                Permission p = Permission.values()[Integer.parseInt(query)];
                return userRepository.filterByPermission(p);
            default:
                return getAll();
        }
    }

    @Transactional
    public List<UserEntity> removeUser(final String userId) throws UsernameNotFoundException {
        userRepository.delete(userRepository.getOne(Integer.parseInt(userId)));
        return userRepository.findAll();
    }

    @Transactional
    public String getLogin(final String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.get(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user with login \"" + username + "\" was not found"));
        return user.getLogin();
    }

}
