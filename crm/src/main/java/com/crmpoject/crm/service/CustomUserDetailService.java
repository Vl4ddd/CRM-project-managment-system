package com.crmpoject.crm.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.crmpoject.crm.entities.User.User;
import com.crmpoject.crm.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login).getFirst();
        if (user == null) {
            throw new UsernameNotFoundException("User  not found with username: " + login);
        }

        // Преобразование User в UserDetails

        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(),
                mapRoles(user));
        return userDetail;

    }


    public Collection<GrantedAuthority> mapRoles(User user) {
        return List.of(user.getRole()) // Получаем роль пользователя
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name())) // Преобразуем в GrantedAuthority
                .collect(Collectors.toList());
    }

}
