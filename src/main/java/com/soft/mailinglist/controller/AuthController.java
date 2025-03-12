package com.soft.mailinglist.controller;

import com.soft.mailinglist.command.LoginCommand;
import com.soft.mailinglist.command.RefreshCommand;
import com.soft.mailinglist.command.RegisterCommand;
import com.soft.mailinglist.command.TokenResponse;
import com.soft.mailinglist.service.AuthService;
import com.soft.mailinglist.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterCommand registerCommand) {
        authService.register(registerCommand);
        log.info("Registered user: {}", registerCommand.getUsername());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginCommand loginCommand) {
        TokenResponse tokensResponse = authService.login(loginCommand);
        log.info("Login successful: {}", loginCommand.getUsername());
        return ResponseEntity.ok(tokensResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody RefreshCommand command) {
        String refreshToken = command.getRefreshToken();
        TokenResponse tokensResponse = tokenService.updatedAccessToken(refreshToken);
        log.info("Refresh token successful: {}", refreshToken);
        return ResponseEntity.ok(tokensResponse);
    }
}
