package com.faby.hostfully.technical_test.controllers;

import com.faby.hostfully.technical_test.domain.dtos.UserDTO;
import com.faby.hostfully.technical_test.domain.dtos.requests.AuthenticateRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.RegisterRequest;
import com.faby.hostfully.technical_test.mappers.AuthMapper;
import com.faby.hostfully.technical_test.services.AuthService;
import com.faby.hostfully.technical_test.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final AuthMapper authMapper;

    public AuthController(AuthService authService, UserService userService, AuthMapper authMapper) {
        this.authService = authService;
        this.userService = userService;
        this.authMapper = authMapper;
    }


    @PostMapping("/register")
    public @Valid UserDTO register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authMapper.toDTO(userService.register(registerRequest));
    }

    @PostMapping
    public @NotEmpty String authenticate(@Valid @RequestBody AuthenticateRequest authenticateRequest) {
        return authService.authenticate(authenticateRequest);
    }
}
