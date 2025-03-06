package com.soft.mailinglist.controller;

import com.soft.mailinglist.command.LoginCommand;
import com.soft.mailinglist.command.RefreshCommand;
import com.soft.mailinglist.command.RegisterCommand;
import com.soft.mailinglist.command.TokenResponse;
import com.soft.mailinglist.service.AuthService;
import com.soft.mailinglist.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterCommand registerCommand) {
        authService.register(registerCommand);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginCommand registerCommand) {
        TokenResponse tokensResponse = authService.login(registerCommand);
        return ResponseEntity.ok(tokensResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshCommand command) {
        String refreshToken = command.getRefreshToken();
        TokenResponse tokensResponse = tokenService.updatedAccessToken(refreshToken);
        return ResponseEntity.ok(tokensResponse);
    }
}
