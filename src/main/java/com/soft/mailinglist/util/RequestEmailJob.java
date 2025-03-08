package com.soft.mailinglist.util;

import com.soft.mailinglist.entity.Request;
import com.soft.mailinglist.enums.RequestStatus;
import com.soft.mailinglist.repository.RequestRepository;
import com.soft.mailinglist.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestEmailJob {
    private final RequestRepository requestRepository;
    private final EmailService emailService;

    @Transactional
    @Scheduled(cron = "0 */2 * * * *")
    public void sendPendingRequests() {

        List<Request> requests = requestRepository.findByStatus(RequestStatus.NOT_COMPLETED)
                .stream()
                .sorted(Comparator.comparing(Request::getCreatedAt))
                .toList();

        requests.forEach(request -> {
            emailService.sendRequestTOEmail(request.getToEmail(), request.getText());
            request.setStatus(RequestStatus.COMPLETED);
            request.setDoneAt(LocalDateTime.now());
        });
        requestRepository.saveAll(requests);
    }
}
