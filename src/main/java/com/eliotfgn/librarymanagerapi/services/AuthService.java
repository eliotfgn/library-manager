package com.eliotfgn.librarymanagerapi.services;

import com.eliotfgn.librarymanagerapi.dto.AuthenticationResponse;
import com.eliotfgn.librarymanagerapi.dto.LoginRequest;
import com.eliotfgn.librarymanagerapi.dto.RegisterRequest;
import com.eliotfgn.librarymanagerapi.models.User;
import com.eliotfgn.librarymanagerapi.repositories.RoleRepository;
import com.eliotfgn.librarymanagerapi.repositories.UserRepository;
import com.eliotfgn.librarymanagerapi.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .enabled(true)
                .canReserve(true)
                .phoneNumber(registerRequest.getPhoneNumber())
                .createdDate(Instant.now())
                .build();

        return userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());
        authenticationManager.authenticate(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtProvider.generateToken(loginRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthenticationResponse(token);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
