package com.soft.mailinglist.controller;

import com.soft.mailinglist.command.RequestCommand;
import com.soft.mailinglist.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/registerRequest")
    public ResponseEntity<String> registerRequest(@RequestBody @Valid RequestCommand requestCommand) {
        requestService.registerRequest(requestCommand);
        return ResponseEntity.ok("Request registered successfully");
    }
}
