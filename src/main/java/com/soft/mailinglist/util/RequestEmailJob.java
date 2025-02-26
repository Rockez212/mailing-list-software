package com.soft.mailinglist.util;

import com.soft.mailinglist.entity.Request;
import com.soft.mailinglist.enums.RequestStatus;
import com.soft.mailinglist.exception.RequestsHaveAlreadyBeenCompleted;
import com.soft.mailinglist.repository.RequestRepository;
import com.soft.mailinglist.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestEmailJob {
    private final RequestRepository requestRepository;
    private final EmailService emailService;


    @Scheduled(cron = "0 */2 * * * *")
    public void sendPendingRequests() {
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getStatus() == RequestStatus.NOT_COMPLETED) {
                emailService.sendRequestTOEmail(request.getToEmail(),request.getText());
                request.setStatus(RequestStatus.IS_COMPLETED);
                request.setDoneAt(LocalDateTime.now());
                requestRepository.save(request);
            }
            else {
                throw new RequestsHaveAlreadyBeenCompleted("Requests have already been completed");
            }
        }
    }
}
