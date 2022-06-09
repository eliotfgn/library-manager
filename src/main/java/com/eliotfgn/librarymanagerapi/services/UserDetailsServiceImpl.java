package com.eliotfgn.librarymanagerapi.services;

import com.eliotfgn.librarymanagerapi.models.Role;
import com.eliotfgn.librarymanagerapi.models.User;
import com.eliotfgn.librarymanagerapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+username+" not found."));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //user.getRoles().forEach((role) -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }
}
