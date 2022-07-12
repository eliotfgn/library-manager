package com.eliotfgn.librarymanagerapi.controllers;

import com.eliotfgn.librarymanagerapi.dto.AuthenticationResponse;
import com.eliotfgn.librarymanagerapi.dto.LoginRequest;
import com.eliotfgn.librarymanagerapi.dto.RegisterRequest;
import com.eliotfgn.librarymanagerapi.models.User;
import com.eliotfgn.librarymanagerapi.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin({"*"})
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterRequest registerRequest) {
        User user = authService.signup(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @GetMapping
    public ResponseEntity<?> all(){
        return ResponseEntity.ok().body(authService.getAll());
    }
}
