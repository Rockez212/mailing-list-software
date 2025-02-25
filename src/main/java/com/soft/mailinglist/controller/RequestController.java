package com.soft.mailinglist.controller;

import com.soft.mailinglist.command.RequestCommand;
import com.soft.mailinglist.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @Transactional
    public ResponseEntity<String> registerRequest(@RequestBody @Valid RequestCommand requestCommand) {
        requestService.registerRequest(requestCommand);
        return ResponseEntity.ok("Request registered successfully");
    }
}
