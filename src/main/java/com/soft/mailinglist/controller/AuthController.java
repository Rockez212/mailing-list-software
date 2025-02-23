package com.soft.mailinglist.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthCommand command) {
        //
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterCommand command) {
        //
    }
}
