package com.example.SweepStakes.controller;

import com.example.SweepStakes.dto.AuthenticationResponse;
import com.example.SweepStakes.dto.user.AuthUserDto;
import com.example.SweepStakes.dto.user.CreateUserDto;
import com.example.SweepStakes.exception.custom.UserAlreadyExistException;
import com.example.SweepStakes.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody CreateUserDto request
    ) throws UserAlreadyExistException {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthUserDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }




}
