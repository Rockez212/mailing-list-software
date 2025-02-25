package com.soft.mailinglist.controller;

import com.soft.mailinglist.command.LoginCommand;
import com.soft.mailinglist.command.RegisterCommand;
import com.soft.mailinglist.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterCommand registerCommand) {
        authService.register(registerCommand);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCommand registerCommand) {
        String token = authService.login(registerCommand);
        return ResponseEntity.ok(token);
    }
}
