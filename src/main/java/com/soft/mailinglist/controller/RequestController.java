package com.soft.mailinglist.controller;

import com.soft.mailinglist.command.RequestCommand;
import com.soft.mailinglist.service.RequestService;
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
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/register")
    public ResponseEntity<String> registerRequest(@RequestBody @Valid RequestCommand requestCommand) {
        requestService.registerRequest(requestCommand);
        log.info("Registered request: {}", requestCommand.getEmail());
        return ResponseEntity.ok("Request registered successfully");
    }
}
