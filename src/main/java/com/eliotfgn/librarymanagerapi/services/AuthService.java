package com.eliotfgn.librarymanagerapi.services;

import com.eliotfgn.librarymanagerapi.dto.RegisterRequest;
import com.eliotfgn.librarymanagerapi.models.Role;
import com.eliotfgn.librarymanagerapi.models.User;
import com.eliotfgn.librarymanagerapi.repositories.RoleRepository;
import com.eliotfgn.librarymanagerapi.repositories.UserRepository;
import com.eliotfgn.librarymanagerapi.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public User signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .enabled(true)
                .canReserve(true)
                .phoneNumber(registerRequest.getPhoneNumber())
                .createdDate(Instant.now())
                .build();
        Role role = roleRepository.findByName("USER").orElseThrow(()->new RuntimeException("Role not found!"));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

}
