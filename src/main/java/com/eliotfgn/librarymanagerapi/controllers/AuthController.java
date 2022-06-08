package com.eliotfgn.librarymanagerapi.controllers;

import com.eliotfgn.librarymanagerapi.dto.RegisterRequest;
import com.eliotfgn.librarymanagerapi.models.User;
import com.eliotfgn.librarymanagerapi.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterRequest registerRequest) {
        User user = authService.signup(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
