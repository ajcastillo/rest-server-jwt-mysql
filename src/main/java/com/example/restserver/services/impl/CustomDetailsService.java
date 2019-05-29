package com.example.restserver.services.impl;

import com.example.restserver.domain.User;
import com.example.restserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository users;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = users.findByUserName(s);
        return optionalUser.map(user -> {
            Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
