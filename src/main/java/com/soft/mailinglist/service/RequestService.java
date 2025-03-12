package com.soft.mailinglist.service;

import com.soft.mailinglist.command.RequestCommand;
import com.soft.mailinglist.entity.Request;
import com.soft.mailinglist.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    @Transactional
    public void registerRequest(RequestCommand requestCommand) {
        String email = requestCommand.getEmail();
        String text = requestCommand.getText();
        Request newRequest = new Request(email, text);
        requestRepository.save(newRequest);
        log.info("Successfully created a new request");
    }
}
